import sys,os
import pandas as pd
import numpy as np
from sklearn.ensemble import RandomForestClassifier,GradientBoostingClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix,recall_score,precision_score
from sklearn.model_selection import train_test_split


objColumn = ["LIMIT_BAL","SEX","EDUCATION","MARRIAGE","AGE",
             "PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6",
             "BILL_AMT1","BILL_AMT2","BILL_AMT3","BILL_AMT4","BILL_AMT5","BILL_AMT6",
             "PAY_AMT1","PAY_AMT2","PAY_AMT3","PAY_AMT4","PAY_AMT5","PAY_AMT6"]

feature_Column_tree = ["LIMIT_BAL_PROC","SEX","EDUCATION_PROC","MARRIAGE_PROC","AGE_PROC",
             'MONTHS_NO_PAY', 'MONTHS_NO_PAY_TOTAL', 'MONTHS_PAY_ONTIME', 'MONTHS_PAY_ADVANCE',
             'MONTHS_PAY_INSTALLMENT', 'OVERDUE_LAST_PAYMENT', 'BILL_LIMIT_RATION', 'MONTH_PAY_BACK_RATION_AVG']

featrue_Column_log = ["LIMIT_BAL","SEX","EDUCATION","MARRIAGE","AGE",'MONTHS_NO_PAY', 'MONTHS_NO_PAY_TOTAL', 'MONTHS_PAY_ONTIME', 'MONTHS_PAY_ADVANCE',
             'MONTHS_PAY_INSTALLMENT', 'OVERDUE_LAST_PAYMENT', 'BILL_LIMIT_RATION', 'MONTH_PAY_BACK_RATION_AVG']

label = 'default payment next month'


class CustomerCreditPredict:
    def __init__(self, filePath, algo,modelPmmloutputPath):
        self.file_path = filePath
        self.algo = algo
        self.modelPmmloutputPath = modelPmmloutputPath

    def featureProc(self):
        if os.path.exists(self.file_path) == False:
            print('Input file not found,please check...')
            exit(2)
        sourceDt = pd.read_csv(self.file_path,sep = ',',header = 0,index_col = 0)
        oriRowCnt = len(sourceDt)
        sourceDt.dropna(axis = 0, how = 'any', inplace=True)
        sourceDt.drop_duplicates(subset = objColumn, inplace=True)
        finalRowCnt = len(sourceDt)
        print('original data rows:%d,filtered data rows:%d' %(oriRowCnt,finalRowCnt))
        sourceDt = self.__featrueDiscretization(sourceDt)
        trainDt, testDt = self.__featrueGeneration(sourceDt)
        return trainDt, testDt

    def __featrueDiscretization(self, sourceDt):
        print('Data discretion begin:')
        try:
            sourceDt['EDUCATION_PROC'] = sourceDt['EDUCATION']
            #学历
            sourceDt.loc[sourceDt.EDUCATION_PROC >= 3, 'EDUCATION_PROC'] = 4
            #婚姻
            sourceDt['MARRIAGE_PROC'] = sourceDt['MARRIAGE']
            sourceDt.loc[(sourceDt.MARRIAGE_PROC == 0) | (sourceDt.MARRIAGE_PROC == 3), ['MARRIAGE_PROC']] = 3
            #额度
            sourceDt['LIMIT_BAL_PROC'] = sourceDt['LIMIT_BAL']
            sourceDt.loc[sourceDt.LIMIT_BAL_PROC == 10000, 'LIMIT_BAL_PROC'] = 1
            sourceDt.loc[sourceDt.LIMIT_BAL_PROC == 20000, 'LIMIT_BAL_PROC'] = 2
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 20000)&(sourceDt.LIMIT_BAL_PROC <= 40000), 'LIMIT_BAL_PROC'] = 3
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 40000)&(sourceDt.LIMIT_BAL_PROC <= 60000), 'LIMIT_BAL_PROC'] = 4
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 60000)&(sourceDt.LIMIT_BAL_PROC <= 120000), 'LIMIT_BAL_PROC'] = 5
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 120000)&(sourceDt.LIMIT_BAL_PROC <= 200000), 'LIMIT_BAL_PROC'] = 6
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 200000)&(sourceDt.LIMIT_BAL_PROC <= 300000), 'LIMIT_BAL_PROC'] = 7
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 300000)&(sourceDt.LIMIT_BAL_PROC <= 400000), 'LIMIT_BAL_PROC'] = 8
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 400000)&(sourceDt.LIMIT_BAL_PROC <= 500000), 'LIMIT_BAL_PROC'] = 9
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 500000)&(sourceDt.LIMIT_BAL_PROC <= 600000), 'LIMIT_BAL_PROC'] = 10
            sourceDt.loc[(sourceDt.LIMIT_BAL_PROC > 600000), 'LIMIT_BAL_PROC'] = 11
            #年龄
            sourceDt['AGE_PROC'] = sourceDt['AGE']
            sourceDt.loc[sourceDt.AGE_PROC <= 23, 'AGE_PROC'] = 1
            sourceDt.loc[(sourceDt.AGE_PROC > 23) & (sourceDt.AGE_PROC <= 36), 'AGE_PROC'] = 2
            sourceDt.loc[(sourceDt.AGE_PROC > 36) & (sourceDt.AGE_PROC <= 46), 'AGE_PROC'] = 3
            sourceDt.loc[(sourceDt.AGE_PROC > 46) & (sourceDt.AGE_PROC <= 58), 'AGE_PROC'] = 4
            sourceDt.loc[(sourceDt.AGE_PROC > 58) & (sourceDt.AGE_PROC <= 70), 'AGE_PROC'] = 5
            sourceDt.loc[(sourceDt.AGE_PROC > 70), 'AGE_PROC'] = 6
        except Exception as e:
            print('error occur in featrue discretization:', e)
        else:
            print('Done...')
            return sourceDt

    def __featrueGeneration(self, sourceDt):
        print('FeatureGeneration begin:')
        #六个月中未还款总月数
        sourceDt['MONTHS_NO_PAY'] = sourceDt.apply(lambda line: len([re for re in line[["PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6"]] if re > 0]), axis = 1)
        print('phrase1 done...')
        #六个月中延期还款总月数
        sourceDt['MONTHS_NO_PAY_TOTAL'] = sourceDt.apply(lambda line: sum([re for re in line[["PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6"]] if re > 0]), axis = 1)
        print('phrase2 done...')
        #到期按时还款月数
        sourceDt['MONTHS_PAY_ONTIME'] = sourceDt.apply(lambda line: sum([re for re in line[["PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6"]] if re <= 0]), axis = 1)
        print('phrase3 done...')
        #到期提前还款月数
        sourceDt['MONTHS_PAY_ADVANCE'] = sourceDt.apply(lambda line: sum([re for re in line[["PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6"]] if re == -2]), axis = 1)
        print('phrase4 done...')
        #到期分期还款月数
        sourceDt['MONTHS_PAY_INSTALLMENT'] = sourceDt.apply(lambda line: sum([re for re in line[["PAY_0","PAY_2","PAY_3","PAY_4","PAY_5","PAY_6"]] if re == 0]), axis = 1)
        print('phrase5 done...')


        #总还款数占总货款数的比例
        #sourceDt['PAY_BACK_RATION'] = sourceDt.apply(self.__getPayBackRatio, axis = 1)
        #print('phrase2 done...')

        #前两笔货款还款是否有逾期
        sourceDt['OVERDUE_LAST_PAYMENT'] = sourceDt.apply(self.__ifOverdueLastPayment, axis = 1)
        print('phrase6 done...')

        #帐单金额占总额度的比例
        sourceDt['BILL_LIMIT_RATION'] = sourceDt.apply(lambda line: line['BILL_AMT1'] / line['LIMIT_BAL'], axis = 1)
        print('phrase7 done...')

        #每月还款金额占帐单金额的平均比例
        sourceDt['MONTH_PAY_BACK_RATION_AVG'] = sourceDt.apply(self.__getPayBackRationAvg, axis = 1)
        print('phrase8 done...')
        print('All processes done...')
        return train_test_split(sourceDt, test_size = 0.3,random_state= 88)

    def __getPayBackRatio(self,line):
        pay_amt = line[["PAY_AMT1", "PAY_AMT2", "PAY_AMT3", "PAY_AMT4", "PAY_AMT5", "PAY_AMT6"]].sum()
        bill_amt = line['BILL_AMT1']
        return pay_amt / bill_amt

    def __ifOverdueLastPayment(self,line):
        overDue = [re for re in line[["PAY_0","PAY_2"]] if re > 0]
        if len(overDue) > 0:
            return 1
        else:
            return 0

    def __getPayBackRationAvg(self,line):
        billAmt = line[["BILL_AMT2","BILL_AMT3","BILL_AMT4","BILL_AMT5","BILL_AMT6"]].tolist()
        payAmt = line[["PAY_AMT1","PAY_AMT2","PAY_AMT3","PAY_AMT4","PAY_AMT5"]].tolist()
        tmp_colllect = list()
        for idx in (0,1,2,3,4):
            if billAmt[idx] == 0:
                continue
            tmp_colllect.append(payAmt[idx] / billAmt[idx])
        if len(tmp_colllect) == 0:
            return 1
        else:
            return np.average(tmp_colllect)

    def modelTraining(self, trainDt):
        print('model training begin:')
        if self.algo == '1':
            modelAlgo = GradientBoostingClassifier(n_estimators=200,max_depth=10,)
            model = modelAlgo.fit(trainDt[feature_Column_tree], trainDt[label])

        if self.algo == '2':
            modelAlgo = LogisticRegression(max_iter=200)
            model = modelAlgo.fit(trainDt[featrue_Column_log],trainDt[label])
        print('training done...')
        return model

    def modelPerfEvaluation(self,model, testDt):
        if self.algo == '1':
            result = model.predict(testDt[feature_Column_tree])
        if self.algo == '2':
            result = model.predict(testDt[featrue_Column_log])
        precision = precision_score(testDt[label], result, pos_label=0)
        recall = recall_score(testDt[label], result, pos_label=0)
        conf_m = confusion_matrix(testDt[label], result)
        print('testing precision: %.2f%%,testing recall: %.2f%%' % (100 * precision, 100 * recall))
        print('confusion_matrix:',conf_m)

    def modelPMMLTransform(self):
        return 0

if __name__ == '__main__':
    if len(sys.argv) < 3 :
        print('parameters should be four,please check...\n')
        exit(1)
    filePath = sys.argv[1]
    algO = sys.argv[2]
    modelPmmloutputPath = sys.argv[3]
    try:
		#初始化系统参数
        modelEntity = CustomerCreditPredict(filePath, algO, modelPmmloutputPath)
		#模型特征处理(数据预处理、建模特征生成)
        trainDt, testDt = modelEntity.featureProc()
		#模型训练(根据选择的分类模型进行训练)
        model = modelEntity.modelTraining(trainDt)
		#模型分类效果评估
        modelEntity.modelPerfEvaluation(model, testDt)
		#通用PMML模型文件生成
        #modelEntity.modelPMMLTransform()
    except Exception as e:
        print('some error occur',e)
		
    finally:
        print('all training processes done')