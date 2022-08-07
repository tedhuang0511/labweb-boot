package tw.com.ted.dao;

import tw.com.ted.domain.PostBean;

import java.util.Date;
import java.util.List;

public interface PostDAO {
    List<PostBean> selectAll(Integer currentPage, Integer pageSize);

    Long howManyPagesForSelectAll();

    PostBean select(Integer seqno);

    PostBean insert(PostBean bean);

    PostBean update(Integer seqno, String title, Date createDate, Date endDate, String user, String content, String attach);

    boolean delete(Integer seqno);
}
