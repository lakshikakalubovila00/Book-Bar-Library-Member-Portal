package lk.bookbarlibrarymember;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface CommonController<T> {
    
    public ModelAndView getUi();

    public List<T>findAllData();

    public String saveData(T t);

    public String updateData(T t);

    public String deleteData(T t);
}
