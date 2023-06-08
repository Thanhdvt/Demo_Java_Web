package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService{
	
	@Inject 
	private INewsDAO newsDAO;

	public List<NewsModel> findByCategory(Long categoryId) {
		return newsDAO.findByCategoryId(categoryId);
	}

	public NewsModel save(NewsModel newsModel) {
        Long newsId = newsDAO.save(newsModel);
		return newsDAO.findOne(newsId);
	}

	public NewsModel update(NewsModel updateNews) {
		NewsModel oldNews = newsDAO.findOne(updateNews.getId());
		updateNews.setCreatedDate(oldNews.getCreatedDate());
		updateNews.setCreatedBy(oldNews.getCreatedBy());
		newsDAO.update(updateNews);
		return newsDAO.findOne(updateNews.getId());
	}

	public void delete(long[] ids) {
		for(long id: ids) {
			newsDAO.delete(id);
		}
	}

}
