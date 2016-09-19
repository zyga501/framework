package framework.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class DatabaseFramework {
    protected static SqlSessionFactory buildSqlSessionFactory(String batisConfig) {
        try {
            InputStream inputStream = Resources.getResourceAsStream(batisConfig);
            return new SqlSessionFactoryBuilder().build(inputStream);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public <T> T selectOne(String var1) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            T value = sqlSession.selectOne(var1);
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    public <T> T selectOne(String var1, Object var2) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            T value = sqlSession.selectOne(var1, var2);
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    public <E> List<E> selectList(String var1) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            List<E> value = sqlSession.selectList(var1);
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    public <E> List<E> selectList(String var1, Object var2) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            List<E> value = sqlSession.selectList(var1, var2);
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    public int insert(String var1, Object var2) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            int value = sqlSession.insert(var1, var2);
            sqlSession.commit();
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    public int insert(String var1, Object var2, Callable<Boolean> callable) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        int value = 0;
        try {
            value = sqlSession.insert(var1, var2);
            if (callable == null || callable.call()) {
                sqlSession.commit();
            }
            else {
                value = 0;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            value = 0;
        }
        finally {
            sqlSession.close();
        }
        return value;
    }

    public int update(String var1, Object var2) {
        SqlSession sqlSession = sqlSessionFactory().openSession();
        try {
            int value = sqlSession.update(var1, var2);
            sqlSession.commit();
            return value;
        }
        finally {
            sqlSession.close();
        }
    }

    protected abstract SqlSessionFactory sqlSessionFactory();
}
