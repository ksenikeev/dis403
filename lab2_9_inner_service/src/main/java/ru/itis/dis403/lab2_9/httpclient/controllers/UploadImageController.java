package ru.itis.dis403.lab2_9.httpclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dis403.lab2_9.httpclient.service.ImageService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class UploadImageController {

    private final ImageService imageService;

    public UploadImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/uploadimg")
    public String uploadImg(@RequestParam("image") MultipartFile file) {

        try {
            imageService.processImage(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadresult";
    }

    @GetMapping("/showimg")
    public String showImg(Model model) {
        List<String> imgs = imageService.getImgList();
        model.addAttribute("imgs", imgs);
        return "uploadresult";
    }

}
