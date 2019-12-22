package com.kuldiegor.model_class_from_json;

import org.junit.Test;

import static org.junit.Assert.*;

public class MJsonTest {

    @Test
    public void getModelFromJson() {
        MJson mJson = new MJson();
        String actual = mJson.getModelFromJson("{\n" +
                "\t\"someName\":\"test\"\n" +
                "}","TestClass");
        //System.out.println();


        String expected = "@SerializedName(\"someName\")\n" +
                "private String mSomeName;\n" +
                "\n" +
                "public TestClass(){}\n" +
                "\n" +
                "public TestClass(String someName){\n" +
                "\tmSomeName = someName;\n" +
                "}";

        assertEquals(expected,actual);
    }

    @Test
    public void getModelFromJson2() {
        MJson mJson = new MJson();
        String actual = (mJson.getModelFromJson("{\n" +
                "\t\"someName\":\"test\",\n" +
                "\t\"testInt\":0,\n" +
                "\t\"testBoolean\":false,\n" +
                "\t\"testNull\": null,\n" +
                "\t\"testStringArray\":[\"string\"],\n" +
                "\t\"testIntegerArray\":[0],\n" +
                "\t\"testSubClassArray\":[{\n" +
                "\t\t\"someName\":\"test\",\n" +
                "\t\t\"testInt\":0,\n" +
                "\t\t\"testBoolean\":false,\n" +
                "\t\t\"testNull\": null,\n" +
                "\t\t\"testStringArray\":[\"string\"],\n" +
                "\t\t\"testIntegerArray\":[0]\n" +
                "\t}],\n" +
                "\t\"testSubClass\": {\n" +
                "\t\t\"someName\":\"test\",\n" +
                "\t\t\"testInt\":0,\n" +
                "\t\t\"testBoolean\":false,\n" +
                "\t\t\"testNull\": null,\n" +
                "\t\t\"testStringArray\":[\"string\"],\n" +
                "\t\t\"testIntegerArray\":[0]\n" +
                "\t}\n" +
                "}","TestClass"));
        String expected = "@SerializedName(\"someName\")\n" +
                "private String mSomeName;\n" +
                "\n" +
                "@SerializedName(\"testInt\")\n" +
                "private Integer mTestInt;\n" +
                "\n" +
                "@SerializedName(\"testBoolean\")\n" +
                "private Boolean mTestBoolean;\n" +
                "\n" +
                "@SerializedName(\"testNull\")\n" +
                "private Object mTestNull;\n" +
                "\n" +
                "@SerializedName(\"testStringArray\")\n" +
                "private List<String> mTestStringArray;\n" +
                "\n" +
                "@SerializedName(\"testIntegerArray\")\n" +
                "private List<Integer> mTestIntegerArray;\n" +
                "\n" +
                "@SerializedName(\"testSubClassArray\")\n" +
                "private List<TestSubClassArray> mTestSubClassArray;\n" +
                "\n" +
                "@SerializedName(\"testSubClass\")\n" +
                "private TestSubClass mTestSubClass;\n" +
                "\n" +
                "public TestClass(){}\n" +
                "\n" +
                "public TestClass(String someName, Integer testInt, Boolean testBoolean, Object testNull, List<String> testStringArray, List<Integer> testIntegerArray, List<TestSubClassArray> testSubClassArray, TestSubClass testSubClass){\n" +
                "\tmSomeName = someName;\n" +
                "\tmTestInt = testInt;\n" +
                "\tmTestBoolean = testBoolean;\n" +
                "\tmTestNull = testNull;\n" +
                "\tmTestStringArray = testStringArray;\n" +
                "\tmTestIntegerArray = testIntegerArray;\n" +
                "\tmTestSubClassArray = testSubClassArray;\n" +
                "\tmTestSubClass = testSubClass;\n" +
                "}\n" +
                "public static class TestSubClassArray {\n" +
                "\t@SerializedName(\"someName\")\n" +
                "\tprivate String mSomeName;\n" +
                "\t\n" +
                "\t@SerializedName(\"testInt\")\n" +
                "\tprivate Integer mTestInt;\n" +
                "\t\n" +
                "\t@SerializedName(\"testBoolean\")\n" +
                "\tprivate Boolean mTestBoolean;\n" +
                "\t\n" +
                "\t@SerializedName(\"testNull\")\n" +
                "\tprivate Object mTestNull;\n" +
                "\t\n" +
                "\t@SerializedName(\"testStringArray\")\n" +
                "\tprivate List<String> mTestStringArray;\n" +
                "\t\n" +
                "\t@SerializedName(\"testIntegerArray\")\n" +
                "\tprivate List<Integer> mTestIntegerArray;\n" +
                "\t\n" +
                "\tpublic TestSubClassArray(){}\n" +
                "\t\n" +
                "\tpublic TestSubClassArray(String someName, Integer testInt, Boolean testBoolean, Object testNull, List<String> testStringArray, List<Integer> testIntegerArray){\n" +
                "\t\tmSomeName = someName;\n" +
                "\t\tmTestInt = testInt;\n" +
                "\t\tmTestBoolean = testBoolean;\n" +
                "\t\tmTestNull = testNull;\n" +
                "\t\tmTestStringArray = testStringArray;\n" +
                "\t\tmTestIntegerArray = testIntegerArray;\n" +
                "\t}\n" +
                "}\n" +
                "public static class TestSubClass {\n" +
                "\t@SerializedName(\"someName\")\n" +
                "\tprivate String mSomeName;\n" +
                "\t\n" +
                "\t@SerializedName(\"testInt\")\n" +
                "\tprivate Integer mTestInt;\n" +
                "\t\n" +
                "\t@SerializedName(\"testBoolean\")\n" +
                "\tprivate Boolean mTestBoolean;\n" +
                "\t\n" +
                "\t@SerializedName(\"testNull\")\n" +
                "\tprivate Object mTestNull;\n" +
                "\t\n" +
                "\t@SerializedName(\"testStringArray\")\n" +
                "\tprivate List<String> mTestStringArray;\n" +
                "\t\n" +
                "\t@SerializedName(\"testIntegerArray\")\n" +
                "\tprivate List<Integer> mTestIntegerArray;\n" +
                "\t\n" +
                "\tpublic TestSubClass(){}\n" +
                "\t\n" +
                "\tpublic TestSubClass(String someName, Integer testInt, Boolean testBoolean, Object testNull, List<String> testStringArray, List<Integer> testIntegerArray){\n" +
                "\t\tmSomeName = someName;\n" +
                "\t\tmTestInt = testInt;\n" +
                "\t\tmTestBoolean = testBoolean;\n" +
                "\t\tmTestNull = testNull;\n" +
                "\t\tmTestStringArray = testStringArray;\n" +
                "\t\tmTestIntegerArray = testIntegerArray;\n" +
                "\t}\n" +
                "}";
        assertEquals(expected,actual);
    }

    @Test
    public void getModelFromJson3() {
        MJson mJson = new MJson();
        String actual = mJson.getModelFromJson("{\n" +
                "\t\"someName\":\"test\",\n" +
                "\t\"testSubClass\": {\n" +
                "\t\t\"someName\":\"test\",\n" +
                "\t\t\"testSubClass2\": {\n" +
                "\t\t\t\"someName\":\"test\",\n" +
                "\t\t\t\"testSubClass3\": {\n" +
                "\t\t\t\t\"someName\":\"test\",\n" +
                "\t\t\t\t\"testSubClass4\": {\n" +
                "\t\t\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}","TestClass");
        String expected = "@SerializedName(\"someName\")\n" +
                "private String mSomeName;\n" +
                "\n" +
                "@SerializedName(\"testSubClass\")\n" +
                "private TestSubClass mTestSubClass;\n" +
                "\n" +
                "public TestClass(){}\n" +
                "\n" +
                "public TestClass(String someName, TestSubClass testSubClass){\n" +
                "\tmSomeName = someName;\n" +
                "\tmTestSubClass = testSubClass;\n" +
                "}\n" +
                "public static class TestSubClass {\n" +
                "\t@SerializedName(\"someName\")\n" +
                "\tprivate String mSomeName;\n" +
                "\t\n" +
                "\t@SerializedName(\"testSubClass2\")\n" +
                "\tprivate TestSubClass2 mTestSubClass2;\n" +
                "\t\n" +
                "\tpublic TestSubClass(){}\n" +
                "\t\n" +
                "\tpublic TestSubClass(String someName, TestSubClass2 testSubClass2){\n" +
                "\t\tmSomeName = someName;\n" +
                "\t\tmTestSubClass2 = testSubClass2;\n" +
                "\t}\n" +
                "\tpublic static class TestSubClass2 {\n" +
                "\t\t@SerializedName(\"someName\")\n" +
                "\t\tprivate String mSomeName;\n" +
                "\t\t\n" +
                "\t\t@SerializedName(\"testSubClass3\")\n" +
                "\t\tprivate TestSubClass3 mTestSubClass3;\n" +
                "\t\t\n" +
                "\t\tpublic TestSubClass2(){}\n" +
                "\t\t\n" +
                "\t\tpublic TestSubClass2(String someName, TestSubClass3 testSubClass3){\n" +
                "\t\t\tmSomeName = someName;\n" +
                "\t\t\tmTestSubClass3 = testSubClass3;\n" +
                "\t\t}\n" +
                "\t\tpublic static class TestSubClass3 {\n" +
                "\t\t\t@SerializedName(\"someName\")\n" +
                "\t\t\tprivate String mSomeName;\n" +
                "\t\t\t\n" +
                "\t\t\t@SerializedName(\"testSubClass4\")\n" +
                "\t\t\tprivate TestSubClass4 mTestSubClass4;\n" +
                "\t\t\t\n" +
                "\t\t\tpublic TestSubClass3(){}\n" +
                "\t\t\t\n" +
                "\t\t\tpublic TestSubClass3(String someName, TestSubClass4 testSubClass4){\n" +
                "\t\t\t\tmSomeName = someName;\n" +
                "\t\t\t\tmTestSubClass4 = testSubClass4;\n" +
                "\t\t\t}\n" +
                "\t\t\tpublic static class TestSubClass4 {\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}";
        assertEquals(actual,expected);
    }
}