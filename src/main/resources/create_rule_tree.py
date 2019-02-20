import json


class Node:
    def __init__(self):
        self.id = 0
        self.weight = -1
        self.type = ''
        self.son = None

    def __repr__(self):
        return str(self.__dict__)

import random

sub_tree = {}

def recursive_rule_input(available_nodes):

    if len(available_nodes) <= 1 :
        return
    rule_id_index = input("Q2.请从以下规则集选择需要融合的规则集，可供选择的规则集:(注意规则集下标之间请用','号隔开)"+str(available_nodes))

    # 随机数从高位开始 避免和原始的规则集重复，也可以用字符，但是这里控制台不好输入
    new_id = random.randint(100, 200)
    print('【',rule_id_index,'】融合后新召回集随机ID为：',new_id)
    type = int(input("请选择【" + str(new_id) + "】的融合类型：0-加权轮训，1-拼接融合（依次往前融合），2-（其他数据源）"))

    node = Node()
    node.id = new_id
    node.type = type
    sons = []
    for x in rule_id_index.split(","):
        x = int(x)
        available_nodes.remove(x)
        if x in sub_tree.keys():
            son = sub_tree[x]
        else:
            son = Node()
        son.id = x
        if type == 0:
            weight = int(input("请输入【" + str(x) + "】规则集的权重（整型）"))
            son.weight = weight
        sons.append(son)

    available_nodes.append(new_id)
    node.son = sons
    print(node)

    sub_tree[node.id] = node

    recursive_rule_input(available_nodes)

if __name__ == '__main__':
    rule_size = int(input("Q1. 有多少个主规则集?"))
    available_nodes = [available_node for available_node in range(rule_size)]
    tree = recursive_rule_input(available_nodes)
