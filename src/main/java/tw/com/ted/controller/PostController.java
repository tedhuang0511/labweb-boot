package tw.com.ted.controller;


import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.Part;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import tw.com.ted.domain.PostBean;
import tw.com.ted.service.PostService;
import tw.com.ted.util.AwsS3Util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {
    private SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private PostService postService;
    @Autowired
    private AwsS3Util awsS3Util;

    @GetMapping("/allposts/{currentPage}/{pageSize}")
    public ResponseEntity<?> selectAll(@PathVariable("currentPage") Integer currentPage,
                                        @PathVariable("pageSize") Integer pageSize) {
        List<PostBean> result = postService.selectAll(currentPage,pageSize);
        if(result!=null&&!result.isEmpty()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("no data found");
    }

    @GetMapping("/allposts/totalPages")
    public Long howManyPagesForSelectAll() {
        Long res = postService.howManyPagesForSelectAll();
        return res;
    }

    @GetMapping("/post/{seqno}")
    public ResponseEntity<?> select(@PathVariable("seqno") Integer seqno) {
        PostBean result = postService.select(seqno);
        if(result!=null){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.ok("no data found");
    }

    @PostMapping("/newpost")
    public ModelAndView newpost(@RequestParam Map<String,Object> params ,@RequestPart(value="file", required = false) MultipartFile file) throws ParseException, IOException {
        PostBean bean = new PostBean();
        bean.setSeqno(Integer.parseInt((String) params.get("seqno")));
        bean.setTitle((String) params.get("title"));
        bean.setCreateDate(sFormat.parse((String) params.get("createDate")));
        bean.setEndDate(sFormat.parse((String) params.get("endDate")));
        bean.setUser((String) params.get("createUser"));
        bean.setContent((String) params.get("editorDemo"));
        if(file!=null && file.getSize()!=0 && file.getSize()<=2000000){ //檔案超過200kb不上傳
            System.out.println("size= "+file.getSize());
            var inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            var objMetadata = new ObjectMetadata();
            String s3Url = awsS3Util.uploadToS3(filename,inputStream,objMetadata);
            String imgUrl = s3Url.substring(0,s3Url.indexOf("?"));
            bean.setAttach(imgUrl);
        }
        if(bean.getSeqno()==0){
            var result = postService.insert(bean);
            if (result!=null) return new ModelAndView("redirect:/");
        }else{
            var result = postService.update(bean);
            if(result!=null) return new ModelAndView("redirect:/");
        }
        return new ModelAndView("error");
    }

    @DeleteMapping("/delete/{seqno}")
    public ResponseEntity<?> delete(@PathVariable("seqno") Integer seqno) {
        boolean deleted = postService.delete(seqno);
        if (deleted) {
            return ResponseEntity.ok("刪除成功");
        } else {
            return ResponseEntity.ok("err997");
        }
    }
}
