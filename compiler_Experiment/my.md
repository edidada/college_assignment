# 编译器前端项目说明

## 项目概述

这是一个**手写的编译器前端**实验项目，包含完整的词法分析和语法分析功能。

## 核心功能

### 1. 词法分析器 (LexicalAnalyzer)

**功能**：
- 将源代码字符串转换为token序列
- 识别标识符、整数、浮点数、运算符、分隔符等
- 使用有限状态自动机（DFA）进行词法分析

**实现技术**：
- `ConstructionTable.java` - 构建状态转换表
- `AcceptState.java` - 定义接受状态
- 各种字符类型类（Digit、Letter等）

### 2. 语法分析器 (SyntaxParser)

**功能**：
- 对token序列进行语法分析
- 使用预测分析法（LL(1)文法）
- 构建语法树

**实现技术**：
- `ForecastTable.java` - 预测分析表
- 使用访问者模式实现表达式处理
- 非终结符：ExpressionE、ExpressionT、Program、Statement等
- 终结符：Id、Plus、Equal、Semicolon等

## 支持的语法

### 1. 赋值语句
```
Program → StatementList $
StatementList → Statement StatementList | ε
Statement → Id = E ;
```

### 2. 算术表达式
```
E → T E'
E' → + T E' | ε
T → F T'  
T' → * F T' | ε
F → ( E ) | id | num
```

### 3. 完整示例
```
x = 10;
y = 20;
z = x + y;
result = z * 2;
```

## 工作流程

1. **词法分析**：读取源代码 → 识别token → 输出token序列
2. **语法分析**：接收token序列 → 预测分析 → 输出语法树或错误

## 词法分析输出示例

词法分析结果文件格式：
```
值    :类型
```

例如：
```
num1	:Id        # 标识符
=	:Operator   # 运算符
15.5	:Float     # 浮点数
```

## 项目结构

```
compiler_Experiment/
├── data/                    # 测试文件
│   ├── lexical.txt         # 词法分析测试
│   ├── syntax.txt          # 语法分析测试
│   ├── test1.txt ~ test5.txt # 新增测试文件
├── logs/                    # 日志输出目录
│   ├── compiler_test_*.log  # 测试日志
│   ├── *_lexical_out.txt    # 词法分析结果
│   └── *_syntax_out.txt     # 语法分析结果
└── src/main/java/
    ├── Main.java           # 主程序入口
    ├── lexical_Analyzer/   # 词法分析器
    └── syntax_Parser/      # 语法分析器
```

## 使用方法

1. 编译项目：
```bash
mvn compile
```

2. 运行测试：
```bash
mvn exec:java -Dexec.mainClass="Main"
```

3. 查看结果：
- 测试日志：`logs/compiler_test_*.log`
- 词法分析结果：`logs/*_lexical_out.txt`
- 语法分析结果：`logs/*_syntax_out.txt`

## 技术特点

- **完全手写实现**：不依赖lex/yacc等工具
- **经典算法**：使用DFA进行词法分析，LL(1)预测分析进行语法分析
- **设计模式**：访问者模式、工厂模式等
- **教育价值**：典型的编译原理课程实验项目
- **扩展性**：支持赋值语句、算术表达式等复杂语法结构
