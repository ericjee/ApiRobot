package com.vip.yyl.repository.util;

import com.vip.yyl.domain.ApiRequest;
import com.vip.yyl.domain.ApiResponse;
import com.vip.yyl.domain.Conversation;
import com.vip.yyl.service.dto.ApiRequestDTO;
import com.vip.yyl.service.dto.ApiResponseDTO;
import com.vip.yyl.service.dto.ConversationDTO;
import com.vip.yyl.web.rest.util.DTOToEntity;

public class ConversationConverter {

    public static Conversation convertToEntity(ApiRequestDTO apiRequestDTO, ApiResponseDTO apiResponseDTO) {
        Conversation conversation = new Conversation();

        ApiRequest apiRequest = DTOToEntity.toEntity(apiRequestDTO);
        if (apiRequestDTO.getId() != null) {
            conversation.setWorkspaceId(apiRequestDTO.getWorkspaceId());
        }

        conversation.setApiRequest(apiRequest);

        ApiResponse response = DTOToEntity.toEntity(apiResponseDTO);

        conversation.setApiResponse(response);

//        if (apiResponseDTO == null && !apiRequestDTO.getApiUrl().isEmpty()) {
//            response.setBody("Could not connect to " + apiRequestDTO.getApiUrl());
//        } else {
//            if (apiResponseDTO.getBody() != null && !apiResponseDTO.getBody().isEmpty()) {
//                response.setBody(apiResponseDTO.getBody());
////                response.setResponse(apiResponseDTO.getResponse());
//            }
//        }

        return conversation;

    }

    public static ConversationDTO convertToDTO(Conversation conversation) {
        ConversationDTO conversationDTO = new ConversationDTO();
        ApiRequestDTO apiRequestDTO = new ApiRequestDTO();
        ApiRequest apiRequest = conversation.getApiRequest();

        apiRequestDTO.setApiUrl(apiRequest.getApiUrl());
        apiRequestDTO.setParameters(apiRequest.getParameters());
        apiRequestDTO.setMethodType(apiRequest.getMethodType());

        conversationDTO.setApiRequestDTO(apiRequestDTO);

        return conversationDTO;
    }

}
