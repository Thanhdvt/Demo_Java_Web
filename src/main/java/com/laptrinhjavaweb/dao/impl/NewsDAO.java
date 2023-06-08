package com.laptrinhjavaweb.dao.impl;

import java.util.List;
import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO{
	
	public List<NewsModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewsMapper(), categoryId);
	}

	public Long save(NewsModel newsModel) {
		String sql = "INSERT INTO news (title, content, categoryid) VALUES (?, ?, ?)";
        return insert(sql, newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId());  
	}	
	
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news = query(sql, new NewsMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}
	
	public void update(NewsModel updateNews) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?,  thumbnail = ?,"); 
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append("createddate = ?, createdby = ? WHERE id = ?");
		update(sql.toString(), updateNews.getTitle(), updateNews.getThumbnail(), 
		updateNews.getShortDescription(), updateNews.getContent(), updateNews.getCategoryId(), 
		updateNews.getCreatedDate(), updateNews.getCreatedBy(), updateNews.getId());
	}
	
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}
}
