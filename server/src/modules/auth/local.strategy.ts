import { Strategy } from 'passport-local';
import { PassportStrategy } from '@nestjs/passport';
import { Injectable } from '@nestjs/common';
import { AuthService } from './auth.service';
import { User } from '../users/entities/user.entity';
import { BusinessException, ErrorCode } from 'src/common/utils/result/result';

@Injectable()
export class LocalStrategy extends PassportStrategy(Strategy) {
  constructor(private readonly authService: AuthService) {
    super();
  }

  /**
   * 校验用户信息
   * @param username 
   * @param password 
   */
  async validate(username: string, password: string): Promise<User> {
    const user = await this.authService.validateUser(username, password);

    console.log(11111);

    // 登陆未找到用户，提示 登陆失败
    if (!user) {
      throw new BusinessException(ErrorCode.LoginFail);
    }

    return user;
  }
}