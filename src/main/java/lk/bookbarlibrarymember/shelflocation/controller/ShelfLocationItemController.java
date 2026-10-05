package lk.bookbarlibrarymember.shelflocation.controller;

import lk.bookbarlibrarymember.shelflocation.dao.ShelfLocationItemDao;
import lk.bookbarlibrarymember.shelflocation.entity.ShelfLocationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShelfLocationItemController {
    @Autowired
    private ShelfLocationItemDao shelfLocationItemDao;

    @GetMapping(value = "/shelflocations/bydisplaycategoryid/{displaycategoryid}", produces = "application/json")
    public List<ShelfLocationItem> getShelfLocationItemsByDisplayCategory(@PathVariable Integer displaycategoryid) {
        return shelfLocationItemDao.getShelfLocationItemsByDisplayCategory(displaycategoryid);
    }
}
