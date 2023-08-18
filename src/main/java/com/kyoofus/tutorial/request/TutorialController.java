package com.kyoofus.tutorial.request;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kyoofus.tutorial.request.dto.AccessTokenDto;
import com.kyoofus.tutorial.request.dto.TutorialDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tutorial/fetch")
public class TutorialController {

  /** plain/text 반환 */
  @GetMapping("/get-text")
  public ResponseEntity<String> getText(HttpServletRequest request, HttpServletResponse response) {
    String greeting = "반가워요";
    HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.TEXT_PLAIN); // MediaType을 설정해야 한다.
    headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8)); // UTF-8 설정을 해 주어야 한다.
    return new ResponseEntity<String>(greeting, headers, HttpStatus.OK);
  }// :

  /** plain/text 반환 */
  @GetMapping("/get-html")
  public ResponseEntity<String> getHtml(HttpServletRequest request, HttpServletResponse response) {
    String html = "<strong>나는 강하다</strong>";
    HttpHeaders headers = new HttpHeaders();
    // headers.setContentType(MediaType.TEXT_HTML); // MediaType을 설정해야 한다.
    headers.setContentType(new MediaType("text", "html", StandardCharsets.UTF_8)); // UTF-8 설정을 해 주어야 한다.
    return new ResponseEntity<String>(html, headers, HttpStatus.OK);
  }// :

  /** octet-stream으로 이미지 다운로드 */
  @GetMapping(value = "/get-image-octet")
  public ResponseEntity<byte[]> getImageOctet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Mime Type을 image/png로 설정
    headers.set("Content-Disposition", "attachment;filename=" + "gominsi.png");
    // response.setHeader("Content-Disposition", "attachment;filename=" +
    // "gominsi.png");
    InputStream in = getClass().getResourceAsStream("/public/images/tutorial/yirae2.jpg");
    // import org.apache.commons.io.IOUtils;
    return new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);
  }// :


  @GetMapping(value = "get-image")
  public ResponseEntity<byte[]> getImage(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) 
      throws IOException {
    HttpHeaders headers = new HttpHeaders();

    String uploadDir = "f:/upload"; 
    String filePath = uploadDir + "/" + fileName;
    FileInputStream fis = new FileInputStream(filePath);
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM); // Mime Type을 image/png로 설정
    headers.set("Content-Disposition", "attachment;filename=" + fileName);
    return new ResponseEntity<byte[]>(IOUtils.toByteArray(fis), headers, HttpStatus.OK);
  }// :


  /** JavaScript 리턴 */
  @GetMapping("/fetch-javascript")
  public ResponseEntity<String> fetchJavascript(HttpServletRequest request, HttpServletResponse response) {
    String html = "alert('hello!!! 안녕!!!');";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "javascript", StandardCharsets.UTF_8)); // UTF-8 설정을 해 주어야 한다.
    return new ResponseEntity<String>(html, headers, HttpStatus.OK);
  }// :

  /** @PathVariable 사용 */
  @GetMapping("/path-variable/{age}/{name}")
  public ResponseEntity<TutorialDto> getJsonPath(HttpServletRequest request, HttpServletResponse response,
      @PathVariable int age, @PathVariable String name) {
    TutorialDto dto = new TutorialDto();
    dto.setUserName(name);
    dto.setAge(age);
    return new ResponseEntity<TutorialDto>(dto, HttpStatus.OK);
  }// :

  /** @RequestParam 사용 */
  @GetMapping("/request-param")
  public ResponseEntity<TutorialDto> requestParam(HttpServletRequest request, HttpServletResponse response,
      @RequestParam("age") int age, @RequestParam("name") String name) {
    TutorialDto dto = new TutorialDto();
    dto.setUserName(name);
    dto.setAge(age);
    return new ResponseEntity<TutorialDto>(dto, HttpStatus.OK);
  }// :


  /** @RequestParam 사용 */
  @GetMapping("/get-access-token/{authCode}")
  public ResponseEntity<AccessTokenDto> getAccessToken(HttpServletRequest request, HttpServletResponse response,
      @PathVariable("authCode") String authCode) {

        AccessTokenDto dto = AccessTokenDto.builder()
                                 .userEmail("aaa@gmaIl.com")
                                 .userName("ejin")
                                 .userId("aabbcc")
                                 .accessToken("AbcEf*ns9Sx")
                                 .build(); 
    return new ResponseEntity<AccessTokenDto>(dto, HttpStatus.OK);
  }// :


  /** applicatin/json 반환 */
  @PostMapping("/request-body")
  public ResponseEntity<TutorialDto> requestBody(HttpServletRequest request, HttpServletResponse response,
      @RequestBody TutorialDto bean) {
    System.out.println("test");
    return new ResponseEntity<TutorialDto>(bean, HttpStatus.OK);
  }// :

  /** @ModelAttribue 사용. JSON 반환 */
  @GetMapping("/model-attribute")
  public ResponseEntity<TutorialDto> modelAttribute(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute TutorialDto bean) {
    return new ResponseEntity<TutorialDto>(bean, HttpStatus.OK);
  }// :

  @GetMapping("/get-json")
  public ResponseEntity<TutorialDto> getJson() {

    TutorialDto dto = new TutorialDto();
    dto.setUserName("John");
    dto.setAge(20);
    return new ResponseEntity<TutorialDto>(dto, HttpStatus.OK);
  }// :

}/// ~
