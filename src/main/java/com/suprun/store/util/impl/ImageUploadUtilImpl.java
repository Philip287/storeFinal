package com.suprun.store.util.impl;

import com.suprun.store.exception.ServiceException;
import com.suprun.store.util.ImageUploadUtil;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ImageUploadUtilImpl implements ImageUploadUtil {

    private static final Logger LOGGER = LogManager.getLogger();
    private static ImageUploadUtil instance;

    public static final String DEFAULT_IMAGE_NAME = "default.png";
    public static final String IMAGES_URL = "images/";
    private static final String UPLOAD_PROPERTIES_NAME = "properties/upload.properties";
    private static final String DIRECTORY_PROPERTY = "directory";
    private static final String RANDOM_STRING_LENGTH_PROPERTY = "randomStringLength";

    private static final Properties uploadProperties;
    public static final String uploadDirectory;
    public static final int randomStringLength;

    static {
        ClassLoader classLoader = MailUtilImpl.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(UPLOAD_PROPERTIES_NAME)) {
            uploadProperties = new Properties();
            uploadProperties.load(inputStream);
            uploadDirectory = uploadProperties.getProperty(DIRECTORY_PROPERTY);
            randomStringLength = Integer.parseInt(uploadProperties.getProperty(RANDOM_STRING_LENGTH_PROPERTY));
            File fileSaveDir = new File(uploadDirectory);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
        } catch (IOException e) {
            LOGGER.fatal("Couldn't read upload properties file", e);
            throw new RuntimeException("Couldn't read upload properties file", e);
        }
    }

    public static ImageUploadUtil getInstance() {
        if (instance == null) {
            instance = new ImageUploadUtilImpl();
        }
        return instance;
    }

    @Override
    public String uploadImage(Part part) throws ServiceException {

        String picturePath;
        String fileName = part.getSubmittedFileName();
        if (!fileName.isEmpty()) {
            try {
                String randomName = RandomStringUtils.randomAlphanumeric(randomStringLength) + fileName;
                part.write(uploadDirectory + File.separator + randomName);
                picturePath = IMAGES_URL + randomName;
            } catch (IOException e) {
                LOGGER.error("An error occurred during uploading file", e);
                throw new ServiceException("An error occurred during uploading file", e);
            }
        } else {
            picturePath = IMAGES_URL + DEFAULT_IMAGE_NAME;
            LOGGER.info("Default image is chosen");
        }
        return picturePath;
    }

    @Override
    public boolean isDefaultPicturePath(String picturePath) {
        return StringUtils.equals(picturePath, IMAGES_URL + DEFAULT_IMAGE_NAME);
    }

}
