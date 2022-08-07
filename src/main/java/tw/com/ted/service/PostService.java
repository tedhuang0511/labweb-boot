package tw.com.ted.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.ted.dao.PostDAO;
import tw.com.ted.domain.PostBean;
import java.util.List;

@Service
@Transactional
public class PostService {
    @Autowired
    private PostDAO postDAO;

    @Transactional(readOnly = true)
    public List<PostBean> selectAll(Integer currentPage,Integer pageSize) {
        return postDAO.selectAll(currentPage,pageSize);
    }
    @Transactional(readOnly = true)
    public PostBean select(Integer seqno) {
        return postDAO.select(seqno);
    }

    @Transactional(readOnly = true)
    public Long howManyPagesForSelectAll(){return postDAO.howManyPagesForSelectAll();}

    public PostBean insert(PostBean bean) {
        System.out.println("insert input= "+bean);
        PostBean result = null;
        if (bean != null && bean.getSeqno() == 0) {
            result = postDAO.insert(bean);
        }
        return result;
    }
//
    public PostBean update(PostBean bean) {
        System.out.println("update input= "+bean);
        PostBean result = null;
        if(bean!=null && bean.getSeqno()!=0) {
            result = postDAO.update(bean.getSeqno(), bean.getTitle(),
                    bean.getCreateDate(), bean.getEndDate(), bean.getUser(), bean.getContent(), bean.getAttach());
        }
        return result;
    }
//
//    public PostBean deleteImg(PostBean bean, String index) {
//        PostBean result = null;
//        if(bean!=null && bean.getProductId()!=null) {
//            result = postDAO.updateImg(index, bean.getProductId());
//        }
//        return result;
//    }
//
    public boolean delete(Integer seqno) {
        return postDAO.delete(seqno);
    }

}
