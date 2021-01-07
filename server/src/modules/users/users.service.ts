import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository, FindConditions } from 'typeorm';
import { ErrorCode, BusinessException } from 'src/common/utils/result/result';
import { User } from './entities/user.entity';

@Injectable()
export class UsersService {

  constructor(
    @InjectRepository(User) private usersRepository: Repository<User>,
  ) {}
  
  /**
   * 创建用户
   * 1.username 唯一
   * @param user 
   */
  async create(user: User): Promise<User> {
    // const selectUserByName = this.findOne({username: user.username});
    // if (selectUserByName) {
    //   throw new BusinessException(ErrorCode.UsernameRepeatError);
    // }
    return await this.usersRepository.save(user);
  }

  async findOne(params: FindConditions<User>): Promise<User> {
    return await this.usersRepository.findOne(params);
  }

  /**
   * 获取用户列表
   */
  async findUserAll(): Promise<User[]> {
    return await this.usersRepository.find();
  }


}
