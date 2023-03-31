from nltk.tokenize import word_tokenize
import pandas as pd

nltk.download('punkt')


# Считываем данные из файла "keywords.xlsx"
df = pd.read_excel('keywords.xlsx')

# Создаем словарь, где ключами являются эмоции, а значениями - списки ключевых слов
keywords_dict = {}
emotions = ["joy", "sadness", "anger", "disgust", "fear"]

for emotion in emotions:
    keywords_dict[emotion] = []

for index, row in df.iterrows():
    for i in range(0, 5):
        if pd.notna(row[i]):
            keywords_dict[row[i]].append(row[5])


# Функция для определения эмоциональной окраски текста
def analyze_text_emotions(text):
    # Токенизируем текст на отдельные слова
    words = word_tokenize(text.lower())

    # Считаем количество слов каждой эмоции в тексте
    joy_count = sum(1 for word in words if word in keywords_dict[emotions[0]])
    sadness_count = sum(1 for word in words if word in keywords_dict[emotions[1]])
    anger_count = sum(1 for word in words if word in keywords_dict[emotions[2]])
    disgust_count = sum(1 for word in words if word in keywords_dict[emotions[3]])
    fear_count = sum(1 for word in words if word in keywords_dict[emotions[4]])

    # Нормализуем количество слов каждой эмоции к диапазону [0, 1]
    total_count = joy_count + sadness_count + anger_count + disgust_count + fear_count
    if total_count == 0:
        return [0, 0, 0, 0, 0]
    else:
        return [joy_count / total_count, sadness_count / total_count, anger_count / total_count,
                disgust_count / total_count, fear_count / total_count]


text = ""
with open('textForAnalysis.txt', 'r') as file:
    text = file.read()

emotions = analyze_text_emotions(text)
print(emotions)