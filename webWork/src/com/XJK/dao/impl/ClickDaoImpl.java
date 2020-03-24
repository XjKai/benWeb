package com.XJK.dao.impl;

import com.XJK.dao.ClickDao;
import com.XJK.pojo.Click;

import java.util.List;

public class ClickDaoImpl extends BaseDao implements ClickDao {
    @Override
    public int saveClick(Click click) {
        String sql = "insert into t_click(ip, browser, system, cDate) values (?,?,?,?)";
        return update(sql, click.getIp(),click.getBrowser(),click.getSystem(),click.getcDate());
    }

    @Override
    public List<Click> queryAllClick() {
        String sql = "select * from t_click";
        return queryClick(sql);
    }

    @Override
    public int deleteClickById(Long id) {
        String sql = "delete from t_click where id = ?";
        return update(sql, id);
    }

    @Override
    public void reOrderId() {
        String sql1 = "ALTER TABLE `t_click` DROP `id`" ;
        String sql2 = "ALTER TABLE `t_click` ADD `id` BIGINT NOT NULL FIRST" ;
        String sql3 = "ALTER TABLE `t_click` MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(id);";
        update(sql1);
        update(sql2);
        update(sql3);
    }
}
