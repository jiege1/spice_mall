import { observable, configure } from 'mobx'
import ProductModal from './product';

configure({
  enforceActions: 'observed' // 不允许在动作之外进行状态修改
});

export class Store {

  @observable test = '';
  @observable sliders = [
    'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1346142700,2282998096&fm=26&gp=0.jpg',
    'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1346142700,2282998096&fm=26&gp=0.jpg',
    'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1346142700,2282998096&fm=26&gp=0.jpg',
  ];

  @observable product = new ProductModal();

}

export default new Store();
