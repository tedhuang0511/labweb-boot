package tw.com.ted.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import tw.com.ted.domain.PostBean;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

@Repository
public class PostDAOHibernate implements PostDAO{

    @PersistenceContext
    private Session session;

    public Session getSession() {
        return session;
    }

    @Override
    public List<PostBean> selectAll(Integer currentPage, Integer pageSize) {
        CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
        CriteriaQuery<PostBean> criteriaQuery = criteriaBuilder.createQuery(PostBean.class);
        Root<PostBean> root = criteriaQuery.from(PostBean.class);
        TypedQuery<PostBean> typedQuery = this.getSession().createQuery(criteriaQuery);
        typedQuery.setFirstResult((currentPage-1)*pageSize);typedQuery.setMaxResults(pageSize);
        List<PostBean> result = typedQuery.getResultList();
        if(result!=null && !result.isEmpty()) {
            return result;
        }
        return null;
    }

    public Long howManyPagesForSelectAll(){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class); //最後的結果型別
        Root<PostBean> root = query.from(PostBean.class);
        query.select(builder.count(root.get("seqno")));
        var result = (Long) session.createQuery(query).getSingleResult();
        return result;
    }

    @Override
    public PostBean select(Integer seqno) {
        PostBean temp = this.getSession().get(PostBean.class, seqno);
        if(temp!=null) {
            return temp;
        }
        return null;
    }

    @Override
    public PostBean insert(PostBean bean) {
        if(bean!=null && bean.getSeqno()==0) {
            bean.setSeqno(null);
            System.out.println("do INSERT sql");
            this.getSession().save(bean);
            return bean;
        }
        return null;
    }

    @Override
    public PostBean update(Integer seqno, String title, Date createDate, Date endDate, String user, String content, String attach) {
        if(seqno!=0) {
            PostBean temp = this.getSession().get(PostBean.class, seqno);
            if(temp!=null) {
                temp.setTitle(title);
                temp.setCreateDate(createDate);
                temp.setEndDate(endDate);
                temp.setUser(user);
                temp.setContent(content);
                if(attach!=null){
                    temp.setAttach(attach);
                }
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer seqno) {
        if(seqno!=null && seqno!=0) {
            PostBean temp = this.getSession().get(PostBean.class, seqno);
            if(temp!=null) {
                this.getSession().delete(temp);
                return true;
            }
        }
        return false;
    }
}
