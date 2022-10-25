package cn.vorbote.curriculum.views;

/**
 * BaseVo<br>
 * Created at 20/09/2022 17:34
 *
 * @author theod
 */
public abstract sealed class BaseVo permits CourseVo, CurriculumVo, TeacherVo, TermVo, UserVo, ViewCurriculumVo {
}
