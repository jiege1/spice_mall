import { IsNotEmpty, MaxLength, MinLength } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreateUserDto {

  @ApiProperty({ name: '用户名' })
  @MaxLength(10, { message: '用户名不能超过10位！' })
  @IsNotEmpty({ message: '用户名不能为空！' })
  username: string;

  @ApiProperty({ name: '登录密码' })
  @MaxLength(16, { message: '密码不能超过16位！' })
  @MinLength(6, { message: '密码不能小于6位！' })
  @IsNotEmpty({ message: '密码不能为空！' })
  password: string;
  
}