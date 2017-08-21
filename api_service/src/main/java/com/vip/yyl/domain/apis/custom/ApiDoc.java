package com.vip.yyl.domain.apis.custom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vip.yyl.domain.apis.swagger.Swagger;
import com.vip.yyl.service.utils.swagger.core.util.Json;
import org.jongo.marshall.jackson.oid.MongoId;
import org.jongo.marshall.jackson.oid.MongoObjectId;

/**
 * Created by king.yu on 16/10/20.
 */

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonIgnoreProperties(ignoreUnknown = true)
//public class ApiDoc implements Serializable {
//
//    private static final long serialVersionUID = 1L;

public class ApiDoc{
    @MongoId
    @MongoObjectId
    private String _id;
    private Swagger swagger;

    public ApiDoc() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Swagger getSwagger() {
        return swagger;
    }

    public void setSwagger(Swagger swagger) {
        this.swagger = swagger;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((_id == null) ? 0 : _id.hashCode());
        result = prime * result + ((swagger == null) ? 0 : swagger.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ApiDoc other = (ApiDoc) obj;
        if (_id == null) {
            if (other._id != null) {
                return false;
            }
        } else if (!_id.equals(other._id)) {
            return false;
        }


        if (swagger == null) {
            if (other.swagger != null) {
                return false;
            }
        } else if (!swagger.equals(other.swagger)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        String result = super.toString();
        try {
            result = Json.pretty(Json.mapper().writeValueAsString(this));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
