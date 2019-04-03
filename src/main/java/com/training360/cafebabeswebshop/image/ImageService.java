package com.training360.cafebabeswebshop.image;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public Image getImage(long id) {
        try {
            return imageDao.getImage(id);
        } catch (EmptyResultDataAccessException sql) {
            // nothing to do here, HTML handles default image
        }
        return null;
    }

    public void saveImage(Image image) {
        imageDao.saveImage(image);
    }
}
