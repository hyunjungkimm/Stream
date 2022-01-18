package collect;

import java.util.*;

public class Transaction {
    public static void main(String[] args) {
      /*
        // 통화별로 트랜잭션을 그룹화한 코드(명령형 버전)
        //그룹화한 트랜잭션을 저장할 맵을 생성한다.
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        //트랜잭션 리스트를 반복한다.
        for(Transaction transaction : transactions){
            Currency currency = transaction.getCurrency();// 트랜잭션의 통화를 추출한다.
            List<Transaction> transactionsForCurrency =
                    transactionsByCurrencies.get(currency);
            if(transactionsForCurrency == null){//현재 통화를 그룹화하는 맵에 항목이 없으면 항목을 만든다.
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsByCurrencies.add(transaction); // 같은 통화를 가진 트랜잭션 리스트에 현재 탐색 중인 트랜잭션을 추가한다.

            //collect 메서드
            Map<Currency, List<Transaction>>  transactionsByCurrencies = transactions.stream().collect(groupingBy(Transaction::getCurrency));
        }

       */
    }
}
