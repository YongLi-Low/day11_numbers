package ibf2022.ssf.day11numbers.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

// Tell Spring Boot this is the controller
@Controller
@RequestMapping(path = {"/", "/index.html"}, produces = MediaType.TEXT_HTML_VALUE)
public class ImageController {
    
    public String getFileNames() {

        // First generate the list of file names to a list
        List<String> result = new ArrayList<>();
        File[] files = new File("src/main/resources/static/images").listFiles();

        for (File file: files) {
            if (file.isFile()) {
                result.add(file.getName());
            }
        }
        // System.out.println(result.toString());

        //Get the size of the list
        Integer size = result.size();

        Random rand = new Random();

        Integer fileIndex = rand.nextInt(size);

        // return the randomly generated file name
        return result.get(fileIndex);
    }

    // GET / or GET /index.html
    @GetMapping
    public String getImage(Model model) {

        String fileName = getFileNames();
        System.out.printf("The file is %s\n", fileName);
        model.addAttribute("number", "/images/" + fileName);

        return "number";
    }
    
}
