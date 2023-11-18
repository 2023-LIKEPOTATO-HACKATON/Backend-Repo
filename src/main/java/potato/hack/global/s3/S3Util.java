package potato.hack.global.s3;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class S3Util {
    private final S3Client s3Client;

    public File convertMultipartFileToFile(MultipartFile multipartFile, String fileName) {
        File convertedFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return convertedFile;
    }

    public ImageDTO uploadFileToS3(MultipartFile multipartFile, String path) {
        AmazonS3 s3 = s3Client.getAmazonS3();
        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        String fixedFileName = "s_" + fileName;

        File originalFile = convertMultipartFileToFile(multipartFile, fileName);
        File fixedFile = new File(fixedFileName);
        try {
            Thumbnailator.createThumbnail(originalFile, fixedFile, 400, 400);

            String objectPath = path + "/" + fixedFileName;
            String baseUploadUrl = "https://kr.object.ncloudstorage.com/" + s3Client.getBucketName() + "/";
            String url = baseUploadUrl + objectPath;

            s3.putObject(s3Client.getBucketName(), objectPath, fixedFile);
            setAcl(objectPath);

            log.info(url);

            return ImageDTO.builder()
                    .url(url)
                    .objectPath(objectPath)
                    .build();

        } catch (AmazonS3Exception e) { // ACL Exception
            log.info(e.getErrorMessage());
            System.exit(1);
            return null; // if error during upload, return null
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Delete temporary files used when uploading
            assert originalFile != null;
            assert fixedFile != null;
            originalFile.delete();
            fixedFile.delete();
        }
    }

    public void deleteFileFromS3(String objectPath) {
        AmazonS3 s3 = s3Client.getAmazonS3();
        try {
            s3.deleteObject(s3Client.getBucketName(), objectPath);
            log.info("Delete Object successfully");
        } catch (SdkClientException e) {
            e.printStackTrace();
            log.info("Error deleteFileFromS3");
        }
    }

    public void setAcl(String objectPath) {
        AmazonS3 s3 = s3Client.getAmazonS3();
        AccessControlList objectAcl = s3.getObjectAcl(s3Client.getBucketName(), objectPath);
        objectAcl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
        s3.setObjectAcl(s3Client.getBucketName(), objectPath, objectAcl);
    }
}
