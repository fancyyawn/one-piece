package top.zhacker.ms.auth.jwt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import top.zhacker.ms.auth.jwt.common.Response;
import top.zhacker.ms.auth.jwt.common.UserUtil;
import top.zhacker.ms.auth.jwt.model.Draft;
import top.zhacker.ms.auth.jwt.repo.DraftRepo;

import java.util.Date;

/**
 * DATE: 17/2/16 下午9:15 <br>
 * MAIL: zhanghecheng@terminus.io <br>
 * AUTHOR: zhanghecheng
 */
@Slf4j
@RestController
@RequestMapping("/api/drafts")
public class Drafts {

    @Autowired
    private DraftRepo draftRepo;

    @RequestMapping(method = RequestMethod.POST)
    public Response<Draft> create(@RequestBody Draft draft){
        draft.setCreateTime(new Date());
        draft.setLastEditTime(new Date());
        draft.setDraftPublished(false);
        draftRepo.save(draft);
        return Response.ok(draft);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Response<Page<Draft>> draftList(Pageable pageable){
        Page<Draft> page = draftRepo.findAll(pageable);
        log.info("{}", UserUtil.getCurrentUser());
        return Response.ok(page);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response<Draft> detail(@PathVariable String id){
        Draft draft = draftRepo.findOne(id);
        return Response.ok(draft);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Response<Draft> update(@PathVariable String id, @RequestBody Draft draft){
        draft.setId(id);
        draftRepo.save(draft);
        return Response.ok(draft);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response<Boolean> delete(@PathVariable String id){
        draftRepo.delete(id);
        return Response.ok(Boolean.TRUE);
    }
}
