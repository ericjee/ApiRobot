package com.vip.yyl.web.rest;

import com.vip.yyl.domain.BaseNode;
import com.vip.yyl.domain.Tag;
import com.vip.yyl.domain.Workspace;
import com.vip.yyl.repository.NodeRepository;
import com.vip.yyl.repository.TagRepository;
import com.vip.yyl.repository.WorkspaceRepository;
import com.vip.yyl.service.dto.NodeDTO;
import com.vip.yyl.service.dto.TagDTO;
import com.vip.yyl.web.rest.util.EntityToDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TagResource {
    Logger logger = LoggerFactory.getLogger(TagResource.class);

    @Inject
    private WorkspaceRepository workspaceRepository;

    @Inject
    private TagRepository tagRepository;

    @Inject
    private NodeRepository nodeRepository;

    @RequestMapping(value="/workspaces/{workspaceId}/tags", method = RequestMethod.POST, headers = "Accept=application/json")
    public Tag create(@PathVariable("workspaceId") String workspaceId, @RequestBody TagDTO tagDTO) {
	logger.debug("Creating a new tag with information: " + tagDTO);

	Tag tag = new Tag();
	tag.setName(tagDTO.getName());
	tag.setDescription(tagDTO.getDescription());
	Tag savedTag = tagRepository.save(tag);

	// Update workspace
	Workspace workspace = workspaceRepository.findOne(workspaceId);
	workspace.getTags().add(savedTag);
	workspaceRepository.save(workspace);

	return savedTag;
    }

    @RequestMapping(value="/tags", method = RequestMethod.POST, headers = "Accept=application/json")
    public Tag create(@RequestBody TagDTO tagDTO) {
	logger.debug("Creating a new tag with information: " + tagDTO);

	Tag tag = new Tag();
	tag.setName(tagDTO.getName());
	tag.setDescription(tagDTO.getDescription());
	return tagRepository.save(tag);
    }

    @RequestMapping(value="/tags/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public Tag delete(@PathVariable("id") String id) {
	logger.debug("Deleting tag with id: " + id);

	Tag deleted = tagRepository.findOne(id);

	tagRepository.delete(deleted);
	List<Workspace> workspaces = workspaceRepository.findByTags(id);
	//The list has to contain exactly 1 workspace. if is just an extra check
	if (workspaces.size()>0){
	    Workspace workspace = workspaces.get(0);
	    workspace.getTags().remove(id);
	    workspaceRepository.save(workspace);
	}
	return deleted;
    }

    @RequestMapping(value="/workspaces/{workspaceId}/tags", method = RequestMethod.GET)
    public List<Tag> findTagsFromAWorkspace(@PathVariable("workspaceId") String workspaceId) {
	logger.debug("Finding all tags from workspace with id " + workspaceId);

	// TODO: Reverse mapping is required for this
	// return tagRepository.findTagsFromAWorkspace(workspaceId);

	Workspace workspace = workspaceRepository.findOne(workspaceId);
	return workspace == null ? null : workspace.getTags();

    }

    @RequestMapping(value="/tags", method = RequestMethod.GET)
    public List<Tag> findAll() {
	logger.debug("Finding all tags");

	return tagRepository.findAll();
    }

    @RequestMapping(value="/tags/{id}", method = RequestMethod.GET)
    public Tag findById(@PathVariable("id") String id) {
	logger.debug("Finding tag by id: " + id);

	return tagRepository.findOne(id);
    }

    @RequestMapping(value="/workspaces/{workspaceId}/tags/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    public Tag update(@PathVariable("workspaceId") String workspaceId, @PathVariable("id") String id, @RequestBody TagDTO updated) {
	logger.debug("Updating tag with information: " + updated);

	Tag tag = tagRepository.findOne(updated.getId());

	tag.setName(updated.getName());
	tag.setDescription(updated.getDescription());

	tagRepository.save(tag);
	return tag;
    }

    @RequestMapping(value="/workspaces/{workspaceId}/tags/{tagId}/nodes", method = RequestMethod.GET)
    public List<NodeDTO> findNodesByTag(@PathVariable("workspaceId") String workspaceId, @PathVariable("tagId") String tagId,
                                 @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit, @RequestParam(value = "search", required = false) String search, @RequestParam(value = "sortBy", required = false) String sortBy) {
	logger.debug("Finding nodes by tag id: " + tagId);

	int pageNo = 0;
	if (page != null && page > 0) {
	    pageNo = page;
	}

	int numberOfRecords = 10;
	if (limit != null && limit > 0) {
	    numberOfRecords = limit;
	}

	Sort sort = new Sort(Direction.DESC, "lastModifiedDate");
	if("name".equals(sortBy)){
	    sort = new Sort(Direction.ASC, "name");
	} else if ("lastRun".equals(sortBy)){
	    sort = new Sort(Direction.DESC, "lastModifiedDate");
	}else if ("nameDesc".equals(sortBy)){
	    sort = new Sort(Direction.DESC, "name");
	}

	Pageable pageable = new PageRequest(pageNo, numberOfRecords, sort);

	Page<BaseNode> paginatedTaggedNodes = nodeRepository.searchTaggedNodes(tagId, search != null ? search : "", pageable);

	List<BaseNode> taggedNodes = paginatedTaggedNodes.getContent();

	List<NodeDTO> response = new ArrayList<NodeDTO>();
	for(BaseNode item : taggedNodes){
	    response.add(EntityToDTO.toDTO(item));
	}

	return response;
    }

}
