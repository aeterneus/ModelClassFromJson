package com.kuldiegor.model_class_from_json;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MJson {

    public String getModelFromJson(String json,String className){
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        StringBuilder resultBuilder = new StringBuilder();
        List<String> tempLines = getModel(jsonElement.getAsJsonObject(),className);
        for (String line: tempLines){
            if (resultBuilder.length()>0){
                resultBuilder.append("\n");
            }
            resultBuilder.append(line);
        }
        return resultBuilder.toString();
    }

    private String convertClassName(String jsonName){
        String[] words = jsonName.split("_");
        StringBuilder builder = new StringBuilder();
        for (String word:words){
            if (!word.isEmpty()) {
                builder.append(word.substring(0,1).toUpperCase()).append(word.substring(1));
            }
        }
        return builder.toString();
    }

    private String convertParameterName(String jsonName) {
        String className = convertClassName(jsonName);
        return className.substring(0,1).toLowerCase().concat(className.substring(1));
    }

    private String convertFieldName(String jsonName){
        return "m".concat(convertClassName(jsonName));
    }

    private List<String> getModel(JsonObject jsonObject,String jsonName){
        List<Field> fieldList = new ArrayList<>();
        List<String> classLines = new ArrayList<>();

        for (Map.Entry<String,JsonElement> entry:jsonObject.entrySet()){
            JsonElement jsonElement = entry.getValue();
            String type = "Object";
            if (jsonElement.isJsonPrimitive()){
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();

                if (jsonPrimitive.isString()){
                    type = "String";
                } else if (jsonPrimitive.isNumber()){
                    type = "Integer";
                } else if (jsonPrimitive.isBoolean()){
                    type = "Boolean";
                }



            } else if (jsonElement.isJsonArray()){
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                type = "List<Object>";
                if (jsonArray.get(0).isJsonPrimitive()){
                    JsonPrimitive jsonPrimitive = jsonArray.get(0).getAsJsonPrimitive();
                    if (jsonPrimitive.isString()){
                        type = "List<String>";
                    } else if (jsonPrimitive.isNumber()){
                        type = "List<Integer>";
                    } else if (jsonPrimitive.isBoolean()){
                        type = "List<Boolean>";
                    }
                } else if (jsonArray.get(0).isJsonObject()) {
                    type = "List<"+convertClassName(entry.getKey())+">";

                    classLines.add("public static class "+convertClassName(entry.getKey())+" {");
                    List<String> tempLines = getModel(jsonArray.get(0).getAsJsonObject(),entry.getKey());
                    for (int i=0;i<tempLines.size();i++){
                        tempLines.set(i,"\t"+tempLines.get(i));
                    }
                    classLines.addAll(tempLines);
                    classLines.add("}");
                }
                //fieldList.add(new Field(type,entry.getKey()));
            } else if (jsonElement.isJsonObject()){
                type = convertClassName(entry.getKey());
               // fieldList.add(new Field(convertClassName(entry.getKey()),entry.getKey()));
                classLines.add("public static class "+convertClassName(entry.getKey())+" {");
                List<String> tempLines = getModel(jsonElement.getAsJsonObject(),entry.getKey());
                for (int i=0;i<tempLines.size();i++){
                    tempLines.set(i,"\t"+tempLines.get(i));
                }
                classLines.addAll(tempLines);
                classLines.add("}");
            }
            fieldList.add(new Field(type,entry.getKey()));
        }
        List<String> result = new ArrayList<>();
        StringBuilder constructorBuilder = new StringBuilder();
        for (Field field:fieldList){
            if (constructorBuilder.length()>0){
                constructorBuilder.append(", ");
            }
            constructorBuilder.append(field.getType()).append(" ").append(convertParameterName(field.getJsonName()));
            result.add("@SerializedName(\""+field.getJsonName()+"\")");
            result.add("private "+field.getType()+" "+convertFieldName(field.getJsonName())+";");
            result.add("");
        }
        if (!fieldList.isEmpty()) {
            result.add("public " + convertClassName(jsonName) + "(){}");
            result.add("");
            result.add("public " + convertClassName(jsonName) + "(" + constructorBuilder.toString() + "){");
            for (Field field : fieldList) {
                result.add("\t" + convertFieldName(field.getJsonName()) + " = " + convertParameterName(field.getJsonName()) + ";");
            }
            result.add("}");
            result.addAll(classLines);
        }
        return  result;
    }
}
