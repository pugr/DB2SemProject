package cz.zcu.kiv.eegmongo.controller;

import cz.zcu.kiv.eegmongo.controller.validator.UserValidator;
import cz.zcu.kiv.eegmongo.crossstore.domain.Scenario;
import cz.zcu.kiv.eegmongo.crossstore.domain.SubjectDocument;
import cz.zcu.kiv.eegmongo.crossstore.domain.User;
import cz.zcu.kiv.eegmongo.crossstore.domain.UserInfo;
import cz.zcu.kiv.eegmongo.crossstore.domain.scenarios.ScenariosDocument;
import cz.zcu.kiv.eegmongo.model.UserItem;
import cz.zcu.kiv.eegmongo.repository.DocumentRepositoryService;
import cz.zcu.kiv.eegmongo.repository.SubjectDocumentRepository;
import cz.zcu.kiv.eegmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectDocumentRepository subjectDocumentRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private DocumentRepositoryService documentRepositoryService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String proceedToList() {
        return "redirect:users/list";
    }

    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public ModelAndView getUsers(ModelAndView mav) {

        List<User> users = userRepository.findAll();

        mav.getModel().put("users", users);

        return mav;
    }

    @RequestMapping(value = "/users/detail/{id}", method = RequestMethod.GET)
    public ModelAndView getUserDetails(@PathVariable String id, ModelAndView mav) {

        Long idNumber = Long.parseLong(id);
        User user = userRepository.findOne(idNumber);

        List<Scenario> documents = documentRepositoryService.getAllDocumentsByUser(user);

        mav.addObject("user", user);
        mav.addObject("documents", documents);
        mav.setViewName("users/detail");

        return mav;
    }

    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id) {

        Long idNumber = Long.parseLong(id);

        User user = userRepository.findOne(idNumber);

        documentRepositoryService.deleteAllDocumentsByUser(user);

        userRepository.delete(idNumber);

        return "redirect:/users/list";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable String id) {

        ModelAndView mav = new ModelAndView();
        Long idLong = Long.parseLong(id);
        User user = userRepository.findOne(idLong);
        UserInfo userInfo = user.getUserInfo();
        UserItem editUserCommand = new UserItem();
        editUserCommand.setName(user.getName());
        editUserCommand.setSurname(user.getSurname());
        editUserCommand.setDateOfBirth(userInfo.getDateOfBirth());
        editUserCommand.setDescription(userInfo.getDescription());
        editUserCommand.setGender(userInfo.getGender());
        editUserCommand.setHeight(userInfo.getHeight());
        editUserCommand.setNationality(userInfo.getNationality());

        mav.addObject("userCommand", editUserCommand);
        mav.setViewName("users/newUser");

        return mav;
    }

    @RequestMapping(value = "/users/newUser",method = RequestMethod.GET)
    public Model addUser(Model model) {
        UserItem newUserCommand = new UserItem();
        model.addAttribute("userCommand", newUserCommand);

        return model;
    }

    @RequestMapping(value = "/users/newUser",method = RequestMethod.POST)
    public String createUser(@ModelAttribute("userCommand") @Valid UserItem userItem,
                             BindingResult bindingResult, Map model) {

        userValidator.validate(userItem, bindingResult);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "users/newUser";
        }

        User user = new User();
        user.setName(userItem.getName());
        user.setSurname(userItem.getSurname());

        UserInfo userInfo = new UserInfo();
        userInfo.setDateOfBirth(userItem.getDateOfBirth());
        userInfo.setDescription(userItem.getDescription());
        userInfo.setGender(userItem.getGender());
        userInfo.setHeight(userItem.getHeight());
        userInfo.setNationality(userItem.getNationality());

        user.setUserInfo(userInfo);

        userRepository.save(user);

        model.put("userCommand", userItem);
        return "users/addEditUserSuccess";
    }

    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.POST)
    public String editUserPost(@PathVariable String id,
                               @ModelAttribute("userCommand") @Valid UserItem userItem,
                             BindingResult bindingResult, Map model) {
        userValidator.validate(userItem, bindingResult);

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
            }
            return "users/newUser";
        }

        ModelAndView mav = new ModelAndView();
        Long idLong = Long.parseLong(id);
        User user = userRepository.findOne(idLong);

        user.setName(userItem.getName());
        user.setSurname(userItem.getSurname());

        UserInfo userInfo = user.getUserInfo();
        userInfo.setDateOfBirth(userItem.getDateOfBirth());
        userInfo.setDescription(userItem.getDescription());
        userInfo.setGender(userItem.getGender());
        userInfo.setHeight(userItem.getHeight());
        userInfo.setNationality(userItem.getNationality());

        user.setUserInfo(userInfo);

        userRepository.save(user);

        model.put("userCommand", userItem);
        return "users/addEditUserSuccess";
    }
}
