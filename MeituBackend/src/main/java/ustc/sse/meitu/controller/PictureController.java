package ustc.sse.meitu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.meitu.pojo.Picture;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.service.PictureService;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping("/add")
    public String add(Picture picture) {
        if (picture != null && picture.getName() != null && picture.getUid() != 0)
            pictureService.add(picture);
        return "home";
    }

}
