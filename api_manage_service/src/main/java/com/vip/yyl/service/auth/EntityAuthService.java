//package com.vip.yyl.service.auth;
//
//import com.mongodb.*;
//import org.bson.types.ObjectId;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class EntityAuthService {
//
//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    public DBObject authenticate(JSONObject userDTO, String projectId) throws Exception{
//
//	DBCollection dbCollection = mongoTemplate.getCollection(projectId+"_User");
//
//	BasicDBObject query = new BasicDBObject();
//	query.append("username", userDTO.get("username"));
//
//	DBObject user = dbCollection.findOne(query);
//	if(user == null){
//	    throw new Exception("User not found");
//	}
//
//	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	BasicDBObject auth = null;
//	if(encoder.matches((String)userDTO.get("password"),(String)user.get("password"))){
//	    auth = new BasicDBObject();
//	    auth.append("user", new DBRef(projectId+"_User",user.get( "_id" ))).append("expireAt", new Date(System.currentTimeMillis() + 3600 * 1000));
//	    auth.put("projectId", projectId);
//
//	    DBCollection dbCollectionAuth = mongoTemplate.getCollection("EntityAuth");
//	    dbCollectionAuth.insert(auth);
//	}else{
//	   throw new Exception("Invalid password");
//	}
//
//	return auth;
//    }
//
//    public boolean logout(String llt){
//
//	DBCollection dbCollection = mongoTemplate.getCollection("EntityAuth");
//
//	BasicDBObject queryObject = new BasicDBObject();
//	queryObject.append("_id", new ObjectId(llt));
//	WriteResult result = dbCollection.remove(queryObject);
//
//	return result.getN() == 1;
//    }
//
//    public JSONObject authorize(String projectId, String authToken, String... roles) throws JSONException {
//
//	JSONObject response = new JSONObject();
//	if(authToken == null){
//	    return response.put("success", false).put("msg", "unauthorized");
//	}
//
//	List<String> roleList = Arrays.asList(roles);
//
//	DBCollection dbCollection = mongoTemplate.getCollection("EntityAuth");
//
//	BasicDBObject queryObject = new BasicDBObject();
//	queryObject.append("_id", new ObjectId(authToken));
//
//	DBObject authData = dbCollection.findOne(queryObject);
//
//	if(authData != null && projectId.equals(authData.get("projectId"))) {
//	    DBRef userRef = (DBRef)authData.get("user");
//	    DBObject user = mongoTemplate.getCollection(userRef.getCollectionName()).findOne(userRef.getId());
//
//	    DBObject roleObj = null;
//	    if(user.containsField("role")){
//		DBRef roleRef = (DBRef)user.get("role");
//		roleObj = mongoTemplate.getCollection(roleRef.getCollectionName()).findOne(roleRef.getId());
//	    }
//
//	    if((roleObj != null && roleList.contains((roleObj.get("name")))) || roleList.contains("USER")){
//		response.put("success", true);
//		response.put("user", userRef);
//
//		authData.put("expireAt", new Date(System.currentTimeMillis() + 3600 * 1000));
//		dbCollection.save(authData);
//	    } else {
//		response.put("success", false).put("msg", "unauthorized");
//	    }
//	} else {
//	    response.put("success", false).put("msg", "unauthorized");
//	}
//
//	return response;
//    }
//}
