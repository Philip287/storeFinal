package com.suprun.store.util;

import com.suprun.store.exception.ServiceException;
import jakarta.servlet.http.Part;

public interface ImageUploadUtil {
    String uploadImage(Part part) throws ServiceException;
    boolean isDefaultPicturePath(String picturePath);
}
