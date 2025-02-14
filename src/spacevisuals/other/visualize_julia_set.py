import numpy as np
import matplotlib.pyplot as plt
import os

def read_julia_set_data(file_path):
    print(file_path)
    with open(file_path, 'r') as file:
        lines = file.readlines()
        data = [int(line.strip()) for line in lines[1:]]  # Skip the first line (file name)
    return np.array(data)

def visualize_julia_set(data):
    plt.hist(data, bins=50, color='blue', edgecolor='black')
    plt.title('Julia Set Escape Iterations')
    plt.xlabel('Iterations')
    plt.ylabel('Frequency')
    # set y scale to log
    # plot sin curve for reference
    x = np.linspace(np.min(data), np.max(data), 1000)
    y = 200/(1+np.exp(.2*(x-np.mean(data))))
    plt.plot(x, y, color='red')
    plt.show()

def main():
    directory = "/Users/jonahschwartzman/Downloads/computerScience/Personal/githubProfile/space-visualizations/"
    for filename in os.listdir(directory):
        if filename.startswith("JuliaSetINSIDE") and filename.endswith(".txt"):
            file_path = os.path.join(directory, filename)
            data = read_julia_set_data(file_path)
            visualize_julia_set(data)

if __name__ == "__main__":
    main()
