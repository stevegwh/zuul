//package chooseyourownadventure;
//
//
//import java.io.FileReader;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class ParseJSON {
//
//    public void getData() {
//		JSONParser parser = new JSONParser();
//        try {
//        	 
//            Object obj = parser.parse(new FileReader(
//                    "/home/forest/eclipse-workspace/chooseyourownadventure/src/chooseyourownadventure/roomData.json"));
// 
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONObject room = (JSONObject) jsonObject.get("room1");
// 
//            String description = (String) room.get("description");
//            JSONArray items = (JSONArray) room.get("items");
//            JSONObject exits = (JSONObject) room.get("exits");
//            
//            System.out.println(description);
//            for(Object item : items) {
//            	System.out.println(item);
//            }
//            System.out.println(exits.get("north"));
// 
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        JSONObject obj = new JSONObject(json);
////        String description = obj.getJSONObject("room1").getString("description"); //sub room1 with currentRoom
////        JSONArray items = obj.getJSONArray("items");
////        JSONObject exits = obj.getJSONObject("exits");
////        System.out.println(exits.getString("north"));
////        System.out.println(description);
////        for (int i = 0; i < items.length(); i++) {
////            String post_id = items.getJSONObject(i).getString("post_id");
////            System.out.println(post_id);
////        }
////
////        JSONArray arr = obj.getJSONArray("posts");
//    }
//}
