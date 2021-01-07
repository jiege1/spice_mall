import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { User } from '../users/entities/user.entity';
import { UsersService } from '../users/users.service';
import { jwtConstants } from './constants';

@Injectable()
export class AuthService {

  constructor(
    private readonly usersService: UsersService,
    private readonly jwtService: JwtService,
  ) {}

  /**
   * 创建token
   * @param username 
   * @param password 
   */
  login(user: User): string {
    return this.jwtService.sign({ 
      username: user.username,
      userId: user.id,
      role: user.role,
    }, { expiresIn: jwtConstants.expiresIn });
  }

  /**
   * 校验用户
   * @param username 
   * @param password 
   */
  async validateUser(username: string, password: string): Promise<User> {
    const user = await this.usersService.findOne({ username });
    if (user && user.password === password) {
      delete user.password;
      return user;
    }
    return null;
  }

}
