package cn.mayanan.dao;

import cn.mayanan.beans.Emp;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.List;

public class EmpDao {
    JdbcTemplate template;
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
    public int save(Emp p) {
        String sql = "insert into emp99(name,salary,designation) values('"+p.getName()+"', '"+p.getSalary()+"', '"+p.getDesignation()+"')";
        return template.update(sql);
    }
    public int update(Emp p) {
        String sql = "update emp99 set name='"+p.getName()+"', salary='"+p.getSalary()+"', designation='"+p.getDesignation()+"' where id="+p.getId()+"";
        return template.update(sql);
    }
    public int delete(int id) {
        String sql = "delete from emp99 where id="+id+"";
        return template.update(sql);
    }
    public Emp getEmpById(int id) {
        String sql = "select * from emp99 where id=?";
        // new Object[]{id} 对象数组，作为sql查询的参数, 用于替换sql语句中的占位符
        return template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Emp>(Emp.class));
    }
    public List<Emp> getEmployees() {
        // 创建RowMapper的匿名内部类实例，RowMapper是一个泛型接口，这里的泛型类型是Emp
        // 意味着RowMapper将用于将查询结果集的行映射到Emp对象
        return template.query("select * from Emp99",new RowMapper<Emp>() {
            // 这是RowMapper接口必须实现的方法
            public Emp mapRow(ResultSet rs, int row) throws SQLException {
                Emp e=new Emp();
                e.setId(rs.getInt(1));
                e.setName(rs.getString(2));
                e.setSalary(rs.getFloat(3));
                e.setDesignation(rs.getString(4));
                return e;
            }
        });
    }
}










