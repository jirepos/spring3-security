package com.kyoofus.tutorial.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.kyoofus.framework.core.exception.AjaxExceptoin;
import com.kyoofus.framework.core.exception.ErrorCode;
import com.kyoofus.framework.core.util.UlidGenerator;
import com.kyoofus.framework.core.util.io.IoUtils;
import com.kyoofus.framework.core.util.io.web.FileDownloader;
import com.kyoofus.framework.core.util.io.web.FileInfo;
import com.kyoofus.framework.core.util.io.web.FileUploader;
import com.kyoofus.tutorial.request.dto.CkEditorUploadDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tutorial/file")
public class TutorialFileController {

  // public void upload() {
  // Ulid ulid = UlidGenerator.getUlid();
  // String ulidString = ulid.toString();

  // }//:

  @PostMapping(value = "/multi-upload")
  public ResponseEntity<List<FileInfo>> multiFileUpload(@RequestParam("content") String content,
      @RequestParam("file") MultipartFile[] multiFiles) {

    String uploadDir = "f:/upload";

    List<FileInfo> uploadedFiles = new ArrayList<FileInfo>();

    for (MultipartFile f : multiFiles) {
      System.out.println(f.getOriginalFilename());
      System.out.println(f.getSize());
      System.out.println(f.getContentType());

      // f.getBytes()
      String ulid = UlidGenerator.getUlid().toString();
      String extention = IoUtils.getFilenameExtension(f.getOriginalFilename());
      String fileName = ulid + "." + extention;
      try {
        log.debug("=====> fileName:" + fileName);
        FileUploader.upload(uploadDir, fileName, f);

        FileInfo fileInfo = FileInfo.builder()
            .absolutePath(uploadDir + "/" + fileName)
            .name(fileName)
            .build();
        uploadedFiles.add(fileInfo);

      } catch (Exception e) {
        throw new AjaxExceptoin(e.getMessage(), ErrorCode.INTERAL_SERVER_ERROR);
      }
    }
    return ResponseEntity.ok().body(uploadedFiles);
  }//

  @PostMapping(value = "/image-upload")
  public ResponseEntity<CkEditorUploadDto> imageUpload(MultipartHttpServletRequest request) {
    String uploadDir = "f:/upload";
    MultipartFile f = request.getFile("upload");
    System.out.println(f.getOriginalFilename());
    System.out.println(f.getSize());
    System.out.println(f.getContentType());

    // f.getBytes()
    String ulid = UlidGenerator.getUlid().toString();
    String extention = IoUtils.getFilenameExtension(f.getOriginalFilename());
    String fileName = ulid + "." + extention;
    try {
      log.debug("=====> fileName:" + fileName);
      FileUploader.upload(uploadDir, fileName, f);
    } catch (Exception e) {
      throw new AjaxExceptoin(e.getMessage(), ErrorCode.INTERAL_SERVER_ERROR);
    }
    // mav.addObject("uploaded", true);
    // mav.addObject("url", "/tutorial/fetch/get-image?fileName=" + fileName);
    // return mav;
    String url = "http://localhost/tutorial/fetch/get-image?fileName=" + fileName;
    CkEditorUploadDto dto = new CkEditorUploadDto(true, url);
      return ResponseEntity.ok().body(dto);
  }//

  /** 비디오 파셜 다운로드 */
  @GetMapping(value = "/video/{fileName}")
  public ResponseEntity<ResourceRegion> getPartialVideo(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("fileName") String fileName, @RequestHeader HttpHeaders headers) throws Exception {
    UrlResource video = new UrlResource("classpath:/public/media/" + fileName);
    return FileDownloader.partialDownload(request, response, headers, video);
    // ResourceRegion region = resourceRegion(video, headers);
    // return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
    // .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM)).body(region);
  }// :

}/// ~
