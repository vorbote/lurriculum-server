package cn.vorbote.curriculum.requests;

import cn.vorbote.curriculum.plains.Course;
import cn.vorbote.curriculum.plains.Curriculum;
import cn.vorbote.curriculum.plains.Teacher;
import cn.vorbote.curriculum.plains.Term;

import java.util.List;

/**
 * BatchCurriculumRequest<br>
 * Created at 2022/10/7 08:31
 *
 * @author vorbote
 */
public record BatchCurriculumRequest(
        Term term,
        List<Course> courses,
        List<Teacher> teachers,
        List<Curriculum> curriculums
) {
}
