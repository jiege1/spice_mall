import { action, isObservableProp } from "mobx";

export default class Base<S extends {}> {

  /**
   * update方法
   * @param key 
   * @param value 
   */
  @action update<K extends keyof S = keyof S>(key: K, value: S[K]) {
    if (isObservableProp(this, key as string)) {
      this[key as string] = value;
    } else {
      // 不允许更新非监听状态的键值
      console.warn(`${key} is not an observable key!`);
    }
  }

}
