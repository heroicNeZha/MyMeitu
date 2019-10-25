package ustc.sse.meitu.controller;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ustc.sse.meitu.pojo.Picture;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.service.PictureService;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    PictureService pictureService;

    @RequestMapping("/add")
    public String add(Picture picture) {
        if (picture != null && picture.getPname() != null && picture.getUid() != 0)
            pictureService.add(picture);
        return "home";
    }

    @RequestMapping("/all")
    public String list() {
        return "all";
    }

    @RequestMapping("/myPics")
    public String listByUser(int uid) throws IOException {
        List<Picture> pictures = pictureService.list(uid);
        JsonFactory jsonF = new JsonFactory();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonGenerator jg = jsonF.createGenerator(outputStream, JsonEncoding.UTF8);
        jg.useDefaultPrettyPrinter();

        return "myPics" + uid;
    }

}
