import { UserRoleEnum } from "../users/entities/user.entity";

export interface IUserPayload {
  userId: number;
  username: string;
  role: UserRoleEnum;
}
