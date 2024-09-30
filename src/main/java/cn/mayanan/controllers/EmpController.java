package cn.mayanan.controllers;

import cn.mayanan.beans.Emp;
import cn.mayanan.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class EmpController {
    @Autowired
    EmpDao dao;

    @RequestMapping("/empform")
    public String showform(Model m){
        m.addAttribute("command", new Emp());
        return "empform";
    }
    /*It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/
    // 在方法参数上使用@ModelAttribute注解时，它告诉spring mvc框架，该方法参数应该被绑定到web请求的参数（比如表单数据或url查询参数上）
    // 并且这些参数应该被封装到一个模型对象中。
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("emp") Emp emp){
        dao.save(emp);
        return "redirect:/viewemp";//will redirect to viewemp request mapping
    }
    /* It provides list of employees in model object */
    @RequestMapping("/viewemp")
    public String viewemp(Model m){
        List<Emp> list=dao.getEmployees();
        m.addAttribute("list",list);
        return "viewemp";
    }
    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value="/editemp/{id}")
    public String edit(@PathVariable int id, Model m){
        Emp emp=dao.getEmpById(id);
        m.addAttribute("command",emp);
        return "empeditform";
    }
    /* It updates model object. */
    @RequestMapping(value="/editsave",method = RequestMethod.POST)
    public String editsave(@ModelAttribute("emp") Emp emp){
        dao.update(emp);
        return "redirect:/viewemp";
    }
    /* It deletes record for the given id in URL and redirects to /viewemp */
    @RequestMapping(value="/deleteemp/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewemp";
    }
}




