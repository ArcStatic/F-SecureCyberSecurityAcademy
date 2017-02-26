package sec.notebook;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NotebookController {
    private ArrayList<String> contentList;
    
    public NotebookController(){
        this.contentList = new ArrayList<String>();
    }

            
    @RequestMapping(value="/")
    public String home(Model model, @RequestParam(required = false) String content){
        if (content != null && !(content.trim()).isEmpty()){
            this.contentList.add(content);
        }
        
        if (contentList.size() > 10){
            contentList.remove(0);
        }
        model.addAttribute("list", contentList);
        return "index";
    }
    
}
