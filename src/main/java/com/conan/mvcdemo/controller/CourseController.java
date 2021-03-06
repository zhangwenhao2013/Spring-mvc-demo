package com.conan.mvcdemo.controller;

import com.conan.mvcdemo.model.Chapter;
import com.conan.mvcdemo.model.Course;
import com.conan.mvcdemo.model.base.Restful;
import com.conan.mvcdemo.services.CourseService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    /**
     * 请求格式 /courses/view?courseId=111
     *
     * @param courseId 课程ID
     * @param model    Spring mvc 提供的特点的model
     * @return
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
        Course course = courseService.getCoursebyId(courseId);

        log.debug("xxxxxx", courseId);

        model.addAttribute(course);
        return "course_overview";
    }

    /**
     * 请求格式 /courses/view2/courseId
     *
     * @param courseId
     * @param map      Spring  mvc model 的三种形式之一
     * @return
     */
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Object> map) {
        Course course = courseService.getCoursebyId(courseId);
        log.debug("xxxxxx", courseId);
        map.put("course", course);
        return "course_overview";
    }

    @RequestMapping(value = "/view3", method = RequestMethod.GET)
    public String viewCourse2(HttpServletRequest httpServletRequest) {
        Integer courseId = Integer.valueOf(httpServletRequest.getParameter("courseId"));
        Course course = courseService.getCoursebyId(courseId);
        log.debug("xxxxxx", courseId);
        httpServletRequest.setAttribute("course", course);
        return "course_overview";
    }

    /**
     * 直接打开一个节目
     *
     * @return
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }

    /**
     * 保存之后 查询课程
     *
     * @param course
     * @return
     */
   /* @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave1(Course course) {
        course.setCourseId(122);
        return "redirect:view2/" + course.getCourseId();
    }*/
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(@ModelAttribute Course course) {
        course.setCourseId(122);
        return "redirect:view2/" + course.getCourseId();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "course_admin/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/Users/zhangwenhao", System.currentTimeMillis() + file.getOriginalFilename()));
        }
        return "success";
    }

    /**
     * 使用 @ResponseBody 注解标注返回类型 可以直接放对象转为Json
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/json/{courseId}", method = RequestMethod.GET)
    public @ResponseBody
    Course getJson(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        return course;
    }

    /**
     * 使用  ResponseEntity对象实例包装要返回对象的实例 可以直接放对象转为Json
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/json1/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Course> getJson1(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }


    @RequestMapping(value = "/restful/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Restful<Course>> restful(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        Restful<Course> courseRestful = new Restful<Course>();
        courseRestful.setData(course);
        courseRestful.setState(HttpStatus.OK);
        return new ResponseEntity<Restful<Course>>(courseRestful, HttpStatus.OK);
    }

    @RequestMapping(value = "/restful1/{courseId}", method = RequestMethod.GET)
    public @ResponseBody
    Restful<Course> restful1(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        Restful<Course> courseRestful = new Restful<Course>();
        courseRestful.setData(course);
        courseRestful.setState(HttpStatus.OK);
        return courseRestful;
    }

    @RequestMapping(value = "/restfulList/{courseId}", method = RequestMethod.GET)
    public @ResponseBody
    Restful<List<Chapter>> restfulList(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        Restful<List<Chapter>> courseRestful = new Restful<List<Chapter>>();
        courseRestful.setData(course.getChapterList());
        courseRestful.setState(HttpStatus.OK);
        return courseRestful;
    }

    @RequestMapping(value = "/restfulList1/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Restful<List<Chapter>>> restfulList1(@PathVariable("courseId") Integer courseId) {
        Course course = courseService.getCoursebyId(courseId);
        Restful<List<Chapter>> courseRestful = new Restful<List<Chapter>>();
        courseRestful.setData(course.getChapterList());
        courseRestful.setState(HttpStatus.OK);
        return new ResponseEntity<Restful<List<Chapter>>>(courseRestful, HttpStatus.OK);
    }

}
