package cn.vorbote.curriculum.transfers;

/**
 * BaseDto<br>
 * Created at 20/09/2022 17:35
 *
 * @author theod
 */
public abstract sealed class BaseDto permits CourseDto, CurriculumDto, TeacherDto, TermDto, UserDto, ViewCurriculumDto {
}
