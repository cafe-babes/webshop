package com.training360.cafebabeswebshop.image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    public Image getImage(long id) {
        return imageDao.getImage(id);
    }

    public void saveImage(Image image) {
        imageDao.saveImage(image);
    }
}
