package dku.merona.service;

import dku.merona.domain.Post;
import dku.merona.domain.PostImg;
import dku.merona.dto.PostImgDto;
import dku.merona.repository.PostImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostImgService {

    private final PostImgRepository postImgRepository;
    private final S3UploadService s3UploadService;

    public void createPostImgList(List<MultipartFile> multipartFiles, Post post) {
        List<String> fileList = s3UploadService.uploadImage(multipartFiles);

        for (String fileName : fileList) {
            String fileUrl = s3UploadService.getImagePath(fileName);
            PostImgDto postImgDto = new PostImgDto(fileName, fileUrl, post);

            postImgRepository.save(postImgDto.toEntity());
        }
    }

    public void deletePostImgList(Post post) {
        List<PostImg> postImgList = postImgRepository.findAllByPost(post);

        for (PostImg postImg: postImgList) {
            s3UploadService.deleteImage(postImg.getImgName());
        }
    }
}
