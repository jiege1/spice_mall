import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

export const enum UserRoleEnum {
  SuperAdmin = 1,
  User = 2 ,
}

export interface IUser {
  id?: number;
  username?: string;
  password?: string;
  role?: UserRoleEnum;
}

@Entity({ name: 'users' })
export class User implements IUser {

  constructor(props?: User) {
    if (props) {
      this.id = props.id;
      this.username = props.username;
      this.role = props.role;
    }
  }

  @PrimaryGeneratedColumn()
  id: number;

  @Column({ unique: true })
  username: string;

  @Column()
  password?: string;

  @Column()
  role: UserRoleEnum;
}