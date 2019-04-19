package dao;

import com.alibaba.fastjson.JSONObject;
import entity.Article;
import entity.Monkey;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleDaoTest {
    private static ArticleDao articleDao;

    @BeforeClass
    public static void init() throws IOException {
        String resource = "mybatis.xml";
        InputStream is = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();

        // MyBatis能够根据配置文件自动的帮我们生成MonkeyDao的实现类
        articleDao = session.getMapper(ArticleDao.class);
    }

    @Test
    public void getArticleWithCondition(){
        Article articleTemp = new Article();
        articleTemp.setTitle("a title");
        List<Article> articles = articleDao.getArticleWithCondition(articleTemp);
        System.out.println(JSONObject.toJSONString(articles));
        // [...]

        // 实际的SQL语句是这样的 select * from article WHERE title = ?
    }

    @Test
    public void getArticleWithCondition2(){
        Article articleTemp = new Article();
        articleTemp.setTitle("a title");
        articleTemp.setContent("content");
        List<Article> articles = articleDao.getArticleWithCondition(articleTemp);
        System.out.println(JSONObject.toJSONString(articles));
        // [...]

        // 实际的SQL语句是这样的 select * from article WHERE title = ? and content = ?
    }
}