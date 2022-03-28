//package com.ping;
//
//import com.google.gson.*;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Set;
//
//public class JsonArrComp {
//
//    private static Gson gson=new Gson();
//    private static JsonParser parser=new JsonParser();
//    public static boolean same(Object a,Object b)
//    {
//        if(a==null)
//        {
//            return b==null;
//        }
//        return same(gson.toJson(a),gson.toJson(b));
//    }
//    public static boolean same(String s1,String s2)
//    {
//        if(s1==null)
//        {
//            return s2==null;
//        }
//        if(s1.equals(s2))
//        {
//            return true;
//        }
//        JsonElement aElements= parser.parse(s1);
//        JsonElement bElements= parser.parse(s2);
//        if(gson.toJson(aElements).equals(gson.toJson(bElements)))
//        {
//            return true;
//        }
//        return  same(aElements,bElements);
//    }
//    public static boolean same(JsonElement a,JsonElement b)
//    {
//        if(a.isJsonObject()&&b.isJsonObject())
//        {
//            return same((JsonElement) a,(JsonElement) b);
//        } else if (a.isJsonArray() && b.isJsonArray()) {
//
//            return same((JsonArray)a,(JsonArray)b);
//        }
//        else if(a.isJsonPrimitive()&&b.isJsonPrimitive())
//        {
//            return same((JsonPrimitive)a,(JsonPrimitive)b);
//        }
//        else if(a.isJsonNull()&&b.isJsonNull())
//        {
//            return same((JsonNull)a,(JsonNull)b);
//        }
//        else{
//            return false;
//        }
//    }
//    private static boolean same(JsonObject a,JsonObject b)
//    {
//        Set<String> setA=a.keySet();
//        Set<String> setB=b.keySet();
//        if(!setA.equals(setB))
//        {
//            return false;
//        }
//        else{
//            for(String aKey:setA)
//            {
//                if(!same(a.get(aKey),b.get(aKey))){
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//    public static boolean same(JsonArray aArr,JsonArray bArr )
//    {
//        if(aArr.size()!=bArr.size())
//        {
//            return false;
//        }
//        List<JsonElement> aList=toSortedList(aArr);
//        List<JsonElement> bList=toSortedList(bArr);
//        for(int i=0;i<aList.size();i++){
//            if(!same(aList.get(i),bList.get(i)))
//            {
//                return false;
//            }
//    }
//        return true;
//    }
//    public static boolean same(JsonPrimitive jsonPrimitive1,JsonPrimitive jsonPrimitive2)
//    {
//        return jsonPrimitive1.equals(jsonPrimitive2);
//    }
//
//    public static boolean same(JsonNull a,JsonNull b)
//    {
//        return  true;
//    }
//    public static List<JsonElement> toSortedList(JsonArray a)
//    {
//        List<JsonElement> aList=new ArrayList<>();
//        aList.forEach(a::add);
//        aList.sort(Comparator.comparing(gson::toJson));
//        return aList;
//    }
//}
