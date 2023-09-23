import { ChangeStatusPageMethods } from './changeStatus/changeStatus.methods';

describe('Change status as postman', () => {
  const changeStatus = new ChangeStatusPageMethods();

  it('changes the status to Confirmed', () => {
    changeStatus.navigateToPostmanLogin();
    changeStatus.changeOrderStatus('Confirmed');
    changeStatus.verifyOrderStatus('Confirmed');
  });

});
