package Services.Accounting;

import Data.Entities.Accounting.Transaction;
import Data.Repository.IEntityRepository;
import Exceptions.Entities.EntityAlreadyExistException;

import java.util.Random;

public class AccountingService
    implements IAccountingService
{
    public AccountingService(IEntityRepository<Integer, Transaction> transactionRepository)
    {
        _transactionRepository = transactionRepository;
    }

    private IEntityRepository<Integer, Transaction> _transactionRepository;

    @Override
    public void WriteTransaction(Transaction transaction) {
        Random rnd = new Random();
        int rndId = rnd.nextInt();
        transaction.set_id(rndId < 0 ? rndId * -1 : rndId);
        try{
            _transactionRepository.Create(transaction);
        }
        catch (EntityAlreadyExistException e) {
            WriteTransaction(transaction);
        }
    }
}
